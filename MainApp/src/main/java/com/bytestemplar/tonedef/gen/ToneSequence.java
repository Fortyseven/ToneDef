/*******************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2014 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 ******************************************************************************/

package com.bytestemplar.tonedef.gen;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.utils.Alert;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread that plays a multi-frequency audio tone until stopped.
 */
public class ToneSequence implements Runnable
{
    private static final String TAG       = "BT";
    private static final String LOGPREFIX = "[ToneSequence] ";

    private static final int SAMPLE_RATE = 44100;

    public static boolean WAIT_FOR_USER = false;

    private class Segment
    {
        Sine sine_gen[];
        int  duration;
        int  bookmark;

        boolean special_waitforuser = false;
    }

    private final List<Segment> mSegments;
    private       int           mCurSegment;
    private       int           mTotalSamples;
    private       int           mCursorSample;
    private       int           mBufferSize;
    private       short[]       mBuffer;
    private       AudioTrack    mTrack;
    private       Thread        mThread;
    private       boolean       mIsplaying;
    private       String        mDescription;

    private int mIterations;
    private int mCurIteration;

    private Activity mParentActivity = null;

    public ToneSequence( Activity activity )
    {
        mParentActivity = activity;

        mSegments = new ArrayList<ToneSequence.Segment>();

        mTotalSamples = 0;

        resetFlags();

        setDescription( "" );
        setIterations( -1 );
    }

    private synchronized void initAudioTrack() throws Exception
    {
        try {
            mBufferSize = AudioTrack.getMinBufferSize( SAMPLE_RATE, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT );

            mBuffer = new short[mBufferSize];

            mTrack = new AudioTrack( AudioManager.STREAM_MUSIC, SAMPLE_RATE, AudioFormat.CHANNEL_OUT_MONO,
                                     AudioFormat.ENCODING_PCM_16BIT, mBufferSize, AudioTrack.MODE_STREAM );
        }
        catch ( Exception e ) {
            Log.e( TAG, LOGPREFIX + "Error creating AudioTrack: " + e.getMessage() );
        }

        if ( mTrack == null ) {
            throw new Exception( "Error allocating AudioTrack...wtf?" );
        }
    }

    public synchronized void setIterations( int iterations )
    {
        mIterations = iterations;
        mCurIteration = 0;
    }

    /**
     * Defines a segment of an overall sequence. Sequences are iterated
     * through over the lifetime of the playback.
     *
     * @param duration
     *         Length of time the current sequence of tones should
     *         play.
     * @param frequencies
     *         A varargs list of int frequencies to play
     *         simultaneously.
     */
    public synchronized void addSegment( int duration, int... frequencies )
    {
        Segment seg = new Segment();

        seg.sine_gen = new Sine[frequencies.length];
        for ( int i = 0; i < frequencies.length; i++ ) {
            seg.sine_gen[i] = new Sine( frequencies[i] );
        }

        seg.duration = duration;

        mTotalSamples += ( ( SAMPLE_RATE / 1000 ) * seg.duration );
        seg.bookmark = mTotalSamples;
        //m_frequencies = frequencies;
        mSegments.add( seg );
    }

    public synchronized void addUserWaitSegment()
    {
        Segment seg = new Segment();
        seg.special_waitforuser = true;
        mSegments.add( seg );
    }

    /**
     * Defines a segment of an overall sequence. Sequences are iterated
     * through over the lifetime of the playback.
     *
     * @param sequence
     *         A SequenceDefinition object.
     */
    public synchronized void addSegment( SequenceDefinition sequence )
    {
        if ( !sequence.isCommand() ) {
            addSegment( sequence.getDuration(), sequence.getFrequencies() );
        }
    }

    /**
     * @param buffer
     *         Array for audio samples.
     * @param num_samples
     *         Number of samples to retrieve.
     */
    public synchronized boolean getSamples( float[] buffer, int num_samples )
    {
        for ( int i = 0; i < buffer.length; i++ ) {
            buffer[i] = 0;
        }

        Segment segment = mSegments.get( mCurSegment );

        for ( int s = 0; s < num_samples; s++ ) {
            float sample = 0;

            for ( int mf = 0; mf < segment.sine_gen.length; mf++ ) {
                sample += segment.sine_gen[mf].getNextSample();
            }

            mCursorSample++;

            sample /= segment.sine_gen.length;

            buffer[s] = sample;

            if ( mCursorSample >= segment.bookmark ) {
                mCurSegment++;

                if ( mCurSegment >= mSegments.size() ) {
                    mCurSegment = 0;
                    mCursorSample = 0;

                    // are we supposed to run only x times,
                    // or forever?

                    if ( mIterations >= 0 ) {
                        mCurIteration++;
                        if ( mCurIteration >= mIterations ) {
                            mThread.interrupt();
                            return true;
                        }
                    }
                }

                if ( mSegments.get( mCurSegment ).special_waitforuser ) {
                    ToneSequence.WAIT_FOR_USER = true;
                    mCurSegment++;

                    mParentActivity.runOnUiThread( new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            Alert.show( mParentActivity, "Waiting", mParentActivity.getString( R.string.dialing_string_wait_msg ), -1, new Alert.OnClick()
                            {

                                @Override
                                public void action( Context context )
                                {
                                    ToneSequence.WAIT_FOR_USER = false;
                                }
                            } );
                        }

                    } );

                    while ( ToneSequence.WAIT_FOR_USER ) {
                        try {
                            Thread.sleep( 100 );
                        }
                        catch ( InterruptedException e ) {
                            return true;
                        }
                    }

                    if ( mCurSegment >= mSegments.size() ) {
                        mCurSegment = 0;
                        mCursorSample = 0;
                        mThread.interrupt();
                        return true;
                    }

                }

                segment = mSegments.get( mCurSegment );
            } // if mCursorSample >= segment.bookmark
        }
        return false;
    }

    public synchronized void start()
    {
        //Log.d( TAG, LOGPREFIX + "start() " + mDescription );

        if ( mIsplaying ) {
            stop();
        }

        try {
            initAudioTrack();
        }
        catch ( Exception e2 ) {
            Log.e( TAG, LOGPREFIX + "AudioTrack not created...wtf?  Not creating thread." );
            return;
        }

        mTrack.play();

        mThread = new Thread( this );

        //Log.d( TAG, LOGPREFIX + "Starting thread " + mThread.toString() );
        mThread.start();
    }

    public synchronized void stop()
    {
        if ( mThread == null ) {
            return;
        }

        //Log.d( TAG, LOGPREFIX + "Stopping thread " + mThread.toString() );

        mThread.interrupt();
        mIsplaying = false;

        mTrack.flush();
        mTrack.stop();
        mTrack.release();
        mTrack = null;

        resetFlags();
    }

    public synchronized void resetFlags()
    {
        mIsplaying = false;
        // m_force_stop = false;
        mCurSegment = 0;
        mCursorSample = 0;
        mCurIteration = 0;
    }

    public synchronized String getDescription()
    {
        return mDescription;
    }

    /**
     * @param text
     *         Text to describe sequence.
     */
    public synchronized void setDescription( String text )
    {
        mDescription = text.trim();
    }

    @Override
    public void run()
    {
        float samples[] = new float[mBufferSize];
        boolean was_interrupted = false;
        mIsplaying = true;

        while ( !was_interrupted ) {
            try {
                // fetch next bunch of samples, shove them into 'samples'
                getSamples( samples, mBufferSize );

                if ( mBuffer.length < mBufferSize ) {
                    mBuffer = new short[mBufferSize];
                }

                for ( int i = 0; i < mBufferSize; i++ ) {
                    mBuffer[i] = (short) ( samples[i] * Short.MAX_VALUE );
                }

                if ( mTrack != null ) {
                    mTrack.write( mBuffer, 0, mBufferSize );
                }
            }
            catch ( Exception e ) {
                was_interrupted = true;
                Log.e( TAG, LOGPREFIX + "Error while playing back: " + e.toString() );
            }

            if ( Thread.interrupted() ) {
                was_interrupted = true;
            }
        }

        if ( mTrack != null ) {
            mTrack.release();
        }

        mIsplaying = false;
    }
}
