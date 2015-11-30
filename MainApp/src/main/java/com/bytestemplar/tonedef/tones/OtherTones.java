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
    public ToneSequence _jp_dialtone;
    public ToneSequence _jp_ringback;
    public ToneSequence _it_ringback;
    public ToneSequence _it_dialtone;

    public ToneSequence _de_dialtone;

    public String _title = "Other Telephony";
//    private final Activity _parent;

    public OtherTones( Activity parent )
    {
//        this._parent = parent;
        _de_dialtone = new ToneSequence( parent );
        _de_dialtone.addSegment( 250, 425 );
        _de_dialtone.setDescription(
                "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        _jp_dialtone = new ToneSequence( parent );
        _jp_dialtone.addSegment( 250, 400 );
        _jp_dialtone.setDescription(
                "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        _jp_ringback = new ToneSequence( parent );
        _jp_ringback.addSegment( 1000, 384, 416 );
        _jp_ringback.addSegment( 2000, 0 );
        _jp_ringback.setDescription(
                "A ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );

        _it_dialtone = new ToneSequence( parent );
        _it_dialtone.addSegment( 1000, 425);
        _it_dialtone.addSegment( 4000, 0 );
        _it_dialtone.setDescription(
                "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        _it_ringback = new ToneSequence( parent );
        _it_ringback.addSegment( 600, 425 );
        _it_ringback.addSegment( 1000, 0 );
        _it_ringback.addSegment( 200, 425 );
        _it_ringback.addSegment( 200, 0 );
        _it_ringback.setDescription(
                "A ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );

    }
}
