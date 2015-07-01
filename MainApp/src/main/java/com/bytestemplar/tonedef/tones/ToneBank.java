/**
 * ****************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2015 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 * ****************************************************************************
 */

package com.bytestemplar.tonedef.tones;

import android.app.Activity;
import android.util.Log;

import com.bytestemplar.tonedef.gen.SequenceDefinition;
import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.ArrayList;

/**
 * This class represents a collection of tones, representing a class of audio signals, such as DTMF or a BlueBox.
 */
public class ToneBank
{
    private static final String TAG       = "BT";
    private static final String LOGPREFIX = "[ToneBank] ";

    /**
     * This inner class represents an entry in a ToneBank _definition. This object might represent
     * a single, playable keypad tone, or a _sequence of tones, such as a busy signal.
     */

    public class Entry
    {
        public char               _ch;
        public ToneSequence       _sequence;
        public SequenceDefinition _definition;

        public void start()
        {
            if ( _definition.isCommand() ) {
                _definition.execute();
            }
            else {
                _sequence.start();
            }
        }

        public void stop()
        {
            if ( !_definition.isCommand() ) {
                _sequence.stop();
            }
        }
    }

    public ArrayList<Entry> _entries;
    private final Activity _parent;

    public ToneBank( Activity parent )
    {
        this._parent = parent;
        _entries = new ArrayList<>();
    }

    public void addEntry( char ch, SequenceDefinition seq_definition )
    {
        Entry newentry = new Entry();

        newentry._ch = ch;
        newentry._definition = seq_definition;

        if ( !seq_definition.isCommand() ) {
            newentry._sequence = new ToneSequence( _parent );
            newentry._sequence.addSegment( seq_definition );
        }
        _entries.add( newentry );
    }

    public Entry getEntry( char ch )
    {
        for ( Entry entry : _entries ) {
            if ( entry._ch == ch ) {
                return entry;
            }
        }
        Log.e( TAG, LOGPREFIX + "Invalid tonebank entry: " + ch );
        return null;
    }

    public ToneSequence buildToneSequence( int on_duration, int off_duration, int pause_duration, String digits )
    {
        ToneSequence seq = new ToneSequence( this._parent );

        SequenceDefinition silence = new SequenceDefinition( off_duration, 0 );
        SequenceDefinition pause   = new SequenceDefinition( pause_duration, 0 );

        for ( int i = 0; i < digits.length(); i++ ) {
            if ( digits.charAt( i ) == ';' || digits.charAt( i ) == 'W' ) {
                seq.addUserWaitSegment();
            }
            else if ( digits.charAt( i ) == ',' || digits.charAt( i ) == 'P' ) {
                seq.addSegment( pause );
            }
            else if ( getEntry( digits.charAt( i ) ) != null ) {
                SequenceDefinition tone = getEntry( digits.charAt( i ) )._definition;
                tone.setDuration( on_duration );
                seq.addSegment( tone );
                seq.addSegment( silence );
            }
        }
        return seq;
    }
}
