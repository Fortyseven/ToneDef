/*******************************************************************************
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
 ******************************************************************************/

package com.bytestemplar.tonedef.tones;

import android.app.Activity;

import com.bytestemplar.tonedef.gen.SequenceDefinition;

/**
 * A blue box is an electronic device that generates the same tones employed by a telephone
 * operator's dialing console to switch long-distance calls.[1] A blue box is a tool that
 * emerged in the 1960s and '70s; it allowed users to route their own calls by emulating the
 * in-band signaling mechanism that then controlled switching in long distance dialing systems.
 *
 * The most typical use of a blue box was to place free telephone calls. A related device,
 * the black box enabled one to receive calls which were free to the caller. The blue box no
 * longer works in most Western nations, as modern switching systems are now digital and do
 * not use in-band signaling. Instead, signaling occurs on an out-of-band channel which cannot
 * be accessed from the line the caller is using, a system called Common Channel Interoffice
 * Signaling or CCIS.
 *
 * http://en.wikipedia.org/wiki/Blue_box
 *
 * Code	   |700Hz   900Hz   1100Hz  1300Hz  1500Hz  1700Hz
 * --------|-----------------------------------------------
    1	   |X	    X
    2	   |X		        X
    3	   |       X	    X
    4      |X			            X
    5	   |        X		        X
    6	   |	            X	    X
    7      |X				                X
    8	   |        X			            X
    9      | 	            X		        X
    0/10   |			            X	    X
    11/ST3 |X					                    X
    12/ST2 |	    X				                X
    KP     |	            X			            X
    KP/ST2 |			            X		        X
    ST     |			                    X	    X
 *
 *
 */
public class ToneBankBlueBox extends ToneBank
{
    public ToneBankBlueBox(Activity parent)
    {
        super( parent );

        addEntry( '1', new SequenceDefinition( 250, 700, 900 ) );
        addEntry( '2', new SequenceDefinition( 250, 700, 1100 ) );
        addEntry( '3', new SequenceDefinition( 250, 900, 1100 ) );

        addEntry( '4', new SequenceDefinition( 250, 700, 1300 ) );
        addEntry( '5', new SequenceDefinition( 250, 900, 1300 ) );
        addEntry( '6', new SequenceDefinition( 250, 1100, 1300 ) );

        addEntry( '7', new SequenceDefinition( 250, 700, 1500 ) );
        addEntry( '8', new SequenceDefinition( 250, 900, 1500 ) );
        addEntry( '9', new SequenceDefinition( 250, 1100, 1500 ) );

        addEntry( 'K', new SequenceDefinition( 250, 1100, 1700 ) );
        addEntry( '0', new SequenceDefinition( 250, 1300, 1500 ) );
        addEntry( 'S', new SequenceDefinition( 250, 1500, 1700 ) );

        addEntry( 'X', new SequenceDefinition( 250, 2600 ) );
    }
}