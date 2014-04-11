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

package com.hacsoft.tonedef.tones;

import android.app.Activity;

import com.hacsoft.tonedef.gen.SequenceDefinition;

/*********************************************************************
 * Dual-tone multi-frequency signaling (DTMF) is used for telecommunication signaling
 * over analog telephone lines in the voice-frequency band between telephone handsets
 * and other communications devices and the switching center. The version of DTMF that
 * is used in push-button telephones for tone dialing is known as Touch-Tone. It was
 * developed by Western Electric and first used by the Bell System in commerce, using
 * that name as a registered trademark. DTMF is standardized by
 * ITU-T Recommendation Q.23. It is also known in the UK as MF4.
 *
 * Other multi-frequency systems are used for internal signaling within the telephone
 * network.  Introduced by AT&T in 1963, the Touch-Tone system using the telephone keypad
 * gradually replaced the use of rotary dial and has become the industry standard for
 * landline service.
 *
 * http://en.wikipedia.org/wiki/Dual-tone_multi-frequency_signaling
 *
 *      1209    1336    1477    1633
 *     .----------------------------
 * 697 |  1       2       3       A
 * 770 |  4       5       6       B
 * 852 |  7       8       9       C
 * 941 |  *       0       #       D
 *
 *********************************************************************/

public class ToneBankDTMF extends ToneBank
{
    public ToneBankDTMF(Activity parent)
    {
        super( parent );

        addEntry( '1', new SequenceDefinition( 250, 697, 1209 ) );
        addEntry( '2', new SequenceDefinition( 250, 697, 1336 ) );
        addEntry( '3', new SequenceDefinition( 250, 697, 1477 ) );
        addEntry( 'A', new SequenceDefinition( 250, 697, 1633 ) );

        addEntry( '4', new SequenceDefinition( 250, 770, 1209 ) );
        addEntry( '5', new SequenceDefinition( 250, 770, 1336 ) );
        addEntry( '6', new SequenceDefinition( 250, 770, 1477 ) );
        addEntry( 'B', new SequenceDefinition( 250, 770, 1633 ) );

        addEntry( '7', new SequenceDefinition( 250, 852, 1209 ) );
        addEntry( '8', new SequenceDefinition( 250, 852, 1336 ) );
        addEntry( '9', new SequenceDefinition( 250, 852, 1477 ) );
        addEntry( 'C', new SequenceDefinition( 250, 852, 1633 ) );

        addEntry( '*', new SequenceDefinition( 250, 941, 1209 ) );
        addEntry( '0', new SequenceDefinition( 250, 941, 1336 ) );
        addEntry( '#', new SequenceDefinition( 250, 941, 1477 ) );
        addEntry( 'D', new SequenceDefinition( 250, 941, 1633 ) );
    }
}
