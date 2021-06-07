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

import com.bytestemplar.tonedef.gen.ToneSequence;

public class OtherTones
{
    public ToneSequence _fr_dialtone;
    public ToneSequence _jp_dialtone;
    public ToneSequence _jp_ringback;
    public ToneSequence _it_ringback;
    public ToneSequence _it_dialtone;

    public ToneSequence _de_dialtone;

    public String _title = "Other Telephony";

    // https://www.itu.int/ITU-T/inr/forms/files/tones-0203.pdf

    private final int DE_DIALTONE_FREQ = 425;
    private final int DE_RINGBACK_FREQ = 425;

    private final int FR_DIALTONE_FREQ = 440;

    private final int ITALY_FREQ = 425;

    private final int JP_RINGBACK_FREQ1 = 384;
    private final int JP_RINGBACK_FREQ2 = 416;

    // TODO: Move these into a string resource. However, this whole section is going to be redone at some point, so it might not even be worth it.

    private final String DIALTONE_DESC = "A dial tone is a telephony signal used to indicate that the telephone exchange is " +
                                         "working, has recognized an off-hook, and is ready to accept a call.";

    private final String RINGBACK_DESC = "A ringback tone is an audible indication that is heard on the telephone line by " +
                                         "the caller while the phone they are calling is being rung. It is normally a repeated " +
                                         "tone, designed to assure the calling party that the called party's line is ringing, " +
                                         "although the ring-back tone may be out of sync with the ringing signal.";

    public OtherTones( Activity parent )
    {
        /* GERMANY */
        _de_dialtone = new ToneSequence( parent );
        _de_dialtone.addSegment( 250, DE_DIALTONE_FREQ );
        _de_dialtone.setDescription( DIALTONE_DESC );

        /* FRANCE */
        _fr_dialtone = new ToneSequence( parent );
        _fr_dialtone.addSegment( 250, FR_DIALTONE_FREQ );
        _fr_dialtone.setDescription( DIALTONE_DESC );

        /* JAPAN */
        _jp_dialtone = new ToneSequence( parent );
        _jp_dialtone.addSegment( 250, 400 );
        _jp_dialtone.setDescription( DIALTONE_DESC );

        _jp_ringback = new ToneSequence( parent );
        _jp_ringback.addSegment( 1000, JP_RINGBACK_FREQ1, JP_RINGBACK_FREQ2 );
        _jp_ringback.addSegment( 2000, 0 );
        _jp_ringback.setDescription( RINGBACK_DESC );

        /* ITALY */
        _it_ringback = new ToneSequence( parent );
        _it_ringback.addSegment( 1000, ITALY_FREQ );
        _it_ringback.addSegment( 4000, 0 );
        _it_ringback.setDescription( RINGBACK_DESC );

        _it_dialtone = new ToneSequence( parent );
        _it_dialtone.addSegment( 200, ITALY_FREQ );
        _it_dialtone.addSegment( 200, 0 );
        _it_dialtone.addSegment( 600, ITALY_FREQ );
        _it_dialtone.addSegment( 1000, 0 );
        _it_dialtone.setDescription( DIALTONE_DESC );

    }
}
