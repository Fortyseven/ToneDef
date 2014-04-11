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
import com.hacsoft.tonedef.gen.SoundGen;

/**
 * A red box is a phreaking device that generates tones to simulate inserting coins
 * in pay phones, thus fooling the system into completing free calls. In the United
 * States, a nickel is represented by one tone, a dime by two, and a quarter by a
 * set of 5 tones. Any device capable of playing back recorded sounds can potentially
 * be used as a red box. Commonly used devices include modified Radio Shack tone dialers,
 * personal MP3 players, and audio-recording greeting cards.
 *
 * US: The tones are made by playing back 1700 Hz and 2200 Hz tones together. One 66 ms
 *     tone represents a nickel. A set of 2 66 ms tones separated by 66 ms intervals
 *     represent a dime, and a quarter is represented by a set of 5 33 ms tones with
 *     33 ms pauses.
 *
 * UK: In the UK, a 1000 Hz tone for 200 ms represents a 10p coin, and 1000 Hz for
 *     350 ms represents a 50p coin.
 *
 * CA:
 *      Nickel      2200hz      0.06s on
 *      Dime        2200hz      0.06s on, 0.06s off, 2x
 *      Quarter	    2200hz	    33ms on, 33ms off, 5x
 *
 * http://en.wikipedia.org/wiki/Red_box_(phreaking)
 * http://www.hackcanada.com/canadian/payphones/canadian-redbox.html
 */
public class ToneBankRedBox extends ToneBank
{
    public ToneBankRedBox( Activity parent )
    {
        super( parent );

        //name = "RedBox";

        addEntry( '1', new SequenceDefinition( new SequenceDefinition.Command()
        {
            @Override
            public void execute()
            {
                play5Cents();
            }
        } ) );

        addEntry( '2', new SequenceDefinition( new SequenceDefinition.Command()
        {
            @Override
            public void execute()
            {
                play10Cents();
            }
        } ) );

        addEntry( '3', new SequenceDefinition( new SequenceDefinition.Command()
        {
            @Override
            public void execute()
            {
                play25cents();
            }
        } ) );

        addEntry( '4', new SequenceDefinition( new SequenceDefinition.Command()
        {
            @Override
            public void execute()
            {
                play10p();
            }
        } ) );

        addEntry( '5', new SequenceDefinition( new SequenceDefinition.Command() {
            @Override
            public void execute()
            {
                play50p();
            }
        } ) );

        //TODO: Canadian coins
    }

    /*
     * The tones are made by playing back 1700 Hz and 2200 Hz tones
     * together. One 66 ms tone represents a nickel. A set of 2 66 ms tones
     * separated by 66 ms intervals represent a dime. A quarter is
     * represented by a set of 5 33 ms tones with 33 ms pauses.
     */

    public void play5Cents()
    {
        SoundGen.oneShotMultiTonePeriodic( 66, 66, 1, 1700, 2200 );
    }

    public void play10Cents()
    {
        SoundGen.oneShotMultiTonePeriodic( 66, 66, 2, 1700, 2200 );
    }

    public void play25cents()
    {
        SoundGen.oneShotMultiTonePeriodic( 33, 33, 5, 1700, 2200 );
    }

    /*
     * In the UK, a 1000 Hz tone for 200 ms represents a 10p coin, and 1000
     * Hz for 350 ms represents a 50p coin. Prior to this system, the
     * earliest UK pay-on-answer payphones used a resistance, inserted into
     * the loop for one or several short periods, to signal units of money
     * inserted. Phreaks simulated these signals by unscrewing the
     * microphone cover of the handset and inserting into the microphone
     * circuit a resistor in parallel with a press-to-open push-button. This
     * was hard to do inconspicuously in outdoor payphones, and was more
     * common indoors (e.g., in student halls of residence).
     */

    public void play10p()
    {
        SoundGen.oneShotMultiTonePeriodic( 200, 0, 1, 1000 );
    }

    public void play50p()
    {
        SoundGen.oneShotMultiTonePeriodic( 350, 0, 1, 1000 );
    }
}