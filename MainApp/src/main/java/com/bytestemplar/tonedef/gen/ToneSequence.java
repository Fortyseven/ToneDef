/**
 * ****************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 * <p/>
 * Copyright (c) 2015 Bytes Templar
 * http://BytesTemplar.com/
 * <p/>
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 * ****************************************************************************
 */

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

    private static class Segment
    {
        Sine sine_gen[];
        int  duration;
        int  bookmark;

        boolean special_waitforuser = false;
    }

    private final List<Segment> _segments;
    private       int           _cur_segment;
    private       int           _total_samples;
    private       int           _cursor_sample;
    private       int           _buffer_size;
    private       short[]       _buffer;
    private       AudioTrack    _track;
    private       Thread        _thread;
    private       boolean       _is_playing;
    private       String        _description;

    private int _iterations;
    private int _cur_iteration;

    private Activity _parent_activity = null;

    public ToneSequence( Activity activity )
    {
        _parent_activity = activity;

        _segments = new ArrayList<>();

        _total_samples = 0;

        resetFlags();

        setDescription( "" );
        setIterations( -1 );
    }

    private synchronized void initAudioTrack() throws Exception
    {
        try {
            _buffer_size = AudioTrack.getMinBufferSize( SAMPLE_RATE,
                                                        AudioFormat.CHANNEL_OUT_MONO,
                                                        AudioFormat.ENCODING_PCM_16BIT );

            _buffer = new short[_buffer_size];

            _track = new AudioTrack( AudioManager.STREAM_MUSIC,
                                     SAMPLE_RATE,
                                     AudioFormat.CHANNEL_OUT_MONO,
                                     AudioFormat.ENCODING_PCM_16BIT,
                                     _buffer_size,
                                     AudioTrack.MODE_STREAM );
        }
        catch ( Exception e ) {
            Log.e( TAG, LOGPREFIX + "Error creating AudioTrack: " + e.getMessage() );
        }

        if ( _track == null ) {
            throw new Exception( "Error allocating AudioTrack...wtf?" );
        }
    }

    /**
     * @param iterations Number of times this should be played
     */
    public synchronized void setIterations( int iterations )
    {
        _iterations = iterations;
        _cur_iteration = 0;
    }

    /**
     * Defines a segment of an overall _sequence. Sequences are iterated
     * through over the lifetime of the playback.
     *
     * @param duration    Length of time the current _sequence of tones should
     *                    play.
     * @param frequencies A varargs list of int frequencies to play
     *                    simultaneously.
     */
    public synchronized void addSegment( int duration, int... frequencies )
    {
        Segment seg = new Segment();

        seg.sine_gen = new Sine[frequencies.length];
        for ( int i = 0; i < frequencies.length; i++ ) {
            seg.sine_gen[i] = new Sine( frequencies[i] );
        }

        seg.duration = duration;

        _total_samples += ( ( SAMPLE_RATE / 1000 ) * seg.duration );
        seg.bookmark = _total_samples;
        //m_frequencies = frequencies;
        _segments.add( seg );
    }

    public synchronized void addUserWaitSegment()
    {
        Segment seg = new Segment();
        seg.special_waitforuser = true;
        _segments.add( seg );
    }

    /**
     * Defines a segment of an overall _sequence. Sequences are iterated
     * through over the lifetime of the playback.
     *
     * @param sequence A SequenceDefinition object.
     */
    public synchronized void addSegment( SequenceDefinition sequence )
    {
        if ( !sequence.isCommand() ) {
            addSegment( sequence.getDuration(), sequence.getFrequencies() );
        }
    }

    /**
     * @param buffer      Array for audio samples.
     * @param num_samples Number of samples to retrieve.
     */
    public synchronized boolean getSamples( float[] buffer, int num_samples )
    {
        for ( int i = 0; i < buffer.length; i++ ) {
            buffer[i] = 0;
        }

        Segment segment = _segments.get( _cur_segment );

        for ( int s = 0; s < num_samples; s++ ) {
            float sample = 0;

            for ( int mf = 0; mf < segment.sine_gen.length; mf++ ) {
                sample += segment.sine_gen[mf].getNextSample();
            }

            _cursor_sample++;

            sample /= segment.sine_gen.length;

            buffer[s] = sample;

            if ( _cursor_sample >= segment.bookmark ) {
                _cur_segment++;

                if ( _cur_segment >= _segments.size() ) {
                    _cur_segment = 0;
                    _cursor_sample = 0;

                    // are we supposed to run only x times,
                    // or forever?

                    if ( _iterations >= 0 ) {
                        _cur_iteration++;
                        if ( _cur_iteration >= _iterations ) {
                            _thread.interrupt();
                            return true;
                        }
                    }
                }

                if ( _segments.get( _cur_segment ).special_waitforuser ) {
                    ToneSequence.WAIT_FOR_USER = true;
                    _cur_segment++;

                    _parent_activity.runOnUiThread( new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            Alert.show( _parent_activity,
                                        "Waiting",
                                        _parent_activity.getString( R.string.dialing_string_wait_msg ),
                                        -1,
                                        new Alert.OnClick()
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

                    if ( _cur_segment >= _segments.size() ) {

                        _cur_segment = 0;
                        _cursor_sample = 0;
                        _thread.interrupt();
                        return true;

                    }

                }

                segment = _segments.get( _cur_segment );
            } // if _cursor_sample >= segment.bookmark
        }
        return false;
    }

    public synchronized void start()
    {
        if ( _is_playing ) {
            stop();
        }

        try {
            initAudioTrack();
        }
        catch ( Exception e2 ) {
            Log.e( TAG, LOGPREFIX + "AudioTrack not created...wtf?  Not creating thread." );
            return;
        }

        _track.play();

        _thread = new Thread( this );

        _thread.start();
    }

    public synchronized void stop()
    {
        if ( _thread == null ) {
            return;
        }

        _thread.interrupt();
        _is_playing = false;

        _track.flush();
        _track.stop();
        _track.release();
        _track = null;

        resetFlags();
    }

    public synchronized void resetFlags()
    {
        _is_playing = false;
        _cur_segment = 0;
        _cursor_sample = 0;
        _cur_iteration = 0;
    }

    public synchronized String getDescription()
    {
        return _description;
    }

    /**
     * @param text Text to describe _sequence.
     */
    public synchronized void setDescription( String text )
    {
        _description = text.trim();
    }

    @Override
    public void run()
    {
        float   samples[]       = new float[_buffer_size];
        boolean was_interrupted = false;
        _is_playing = true;

        while ( !was_interrupted ) {
            try {
                // fetch next bunch of samples, shove them into 'samples'
                getSamples( samples, _buffer_size );

                if ( _buffer.length < _buffer_size ) {
                    _buffer = new short[_buffer_size];
                }

                for ( int i = 0; i < _buffer_size; i++ ) {
                    _buffer[i] = (short) ( samples[i] * Short.MAX_VALUE );
                }

                if ( _track != null ) {
                    _track.write( _buffer, 0, _buffer_size );
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

        if ( _track != null ) {
            _track.release();
        }

        _is_playing = false;
    }
}
