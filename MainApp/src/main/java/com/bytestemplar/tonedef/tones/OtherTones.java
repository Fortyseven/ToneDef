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

package com.bytestemplar.tonedef.tones;

import android.app.Activity;

import com.bytestemplar.tonedef.gen.ToneSequence;

public class OtherTones
{
    public ToneSequence jp_dialtone;
    public ToneSequence jp_ringback;
    public ToneSequence it_ringback;

    public ToneSequence de_dialtone;

    public String title = "Other Telephony";
    private final Activity parent;

    public OtherTones( Activity parent )
    {

        this.parent = parent;
        de_dialtone = new ToneSequence( parent );
        de_dialtone.addSegment( 250, 425 );
        de_dialtone.setDescription( "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        jp_dialtone = new ToneSequence( parent );
        jp_dialtone.addSegment( 250, 400 );
        jp_dialtone.setDescription( "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        jp_ringback = new ToneSequence( parent );
        jp_ringback.addSegment( 1000, 384, 416 );
        jp_ringback.addSegment( 2000, 0 );
        jp_ringback.setDescription( "A ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );

        it_ringback = new ToneSequence( parent );
        it_ringback.addSegment( 1000, 425 );
        it_ringback.addSegment( 200, 0 );
        it_ringback.addSegment( 200, 425 );
        it_ringback.addSegment( 200, 0 );
        it_ringback.setDescription( "A ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );

    }
}
