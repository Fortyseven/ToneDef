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

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

public class SoundGen
{
    public static final int SAMPLE_WIDTH      = 2;
    public static final int SAMPLE_RATE_IN_HZ = 44100;

    private static final String TAG       = "BT";
    private static final String LOGPREFIX = "[SoundGen] ";

    /**
     * Generates a tone only intended to playback once. (Not potentially held down, like DTMF, etc.)
     *
     * @param tone_duration Duration of tone, in ms
     * @param tone_delay    Delay between meatloaf, in ms
     * @param iterations    How many times to play the tone
     * @param freqs         Which frequencies to play in hz
     */
    public static void oneShotMultiTonePeriodic( final int tone_duration, final int tone_delay, final int iterations, final int... freqs )
    {
        Thread t = new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                Sine freqgen[] = new Sine[freqs.length];

                // Setup out sound waves
                for ( int i = 0; i < freqs.length; i++ ) {
                    freqgen[i] = new Sine( freqs[i] );
                }

                final int ms_size     = (int) Math.ceil( SAMPLE_RATE_IN_HZ / 1000 );
                int
                          buffer_size =
                        ( ms_size ) * ( ( tone_duration + tone_delay ) * iterations ) * SAMPLE_WIDTH;

                float[] samples = new float[buffer_size];
                short[] buffer  = new short[buffer_size];

                int buffer_offset = 0;

                for ( int iteration = 0; iteration < iterations; iteration++ ) {

                    // reset all the sine wave generators

                    for ( int f = 0; f < freqs.length; f++ ) {
                        freqgen[f].reset();
                    }

                    // load the samples into the buffer

                    for ( int i = 0; i < ms_size * tone_duration; i++ ) {
                        float samp = 0.0f;

                        for ( int f = 0; f < freqs.length; f++ ) {
                            samp += freqgen[f].getNextSample();
                        }
                        samples[buffer_offset] = samp / freqs.length;
                        buffer_offset++;
                    }

                    // Load silence for the delay
                    if ( tone_delay > 0 ) {
                        for ( int i = 0; i < ms_size * tone_delay; i++ ) {
                            samples[buffer_offset] = 0;
                            buffer_offset++;
                        }
                    }
                }

                for ( int i = 0; i < buffer_size; i++ ) {
                    buffer[i] = (short) ( samples[i] * Short.MAX_VALUE );
                }

                AudioTrack track = null;

                try {
                    track = new AudioTrack( AudioManager.STREAM_MUSIC, SAMPLE_RATE_IN_HZ,
                                            AudioFormat.CHANNEL_OUT_MONO,
                                            AudioFormat.ENCODING_PCM_16BIT,
                                            buffer_size, AudioTrack.MODE_STREAM );
                }
                catch ( Exception e ) {
                    Log.e( TAG, LOGPREFIX + "Error creating AudioTrack object: " + e.getMessage() );
                }

                if ( track != null ) {
                    track.setPlaybackPositionUpdateListener( new AudioTrack.OnPlaybackPositionUpdateListener()
                    {
                        @Override
                        public void onMarkerReached( AudioTrack track )
                        {
                            //Log.e( "HSD", "Releasing track" );
                            track.release();
                        }

                        @Override
                        public void onPeriodicNotification( AudioTrack track )
                        {
                            // Ignore
                        }
                    } );

                    track.play();
                    track.setNotificationMarkerPosition( buffer_size );
                    track.write( buffer, 0, buffer_size );
                }
            }

        }, "oneShotSoundThread" );

        t.start();
    }
}
