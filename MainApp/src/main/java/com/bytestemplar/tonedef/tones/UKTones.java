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

public class UKTones
{
    public ToneSequence ringback;
    public ToneSequence euro_ringback;
    public ToneSequence dialtone;

    public String title = "UK Telephony";
    private final Activity parent;

    public UKTones( Activity parent )
    {

        this.parent = parent;
        dialtone = new ToneSequence( parent );
        dialtone.addSegment( 250, 350 );
        dialtone.setDescription( "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        ringback = new ToneSequence( parent );
        ringback.addSegment( 400, 400, 450 );
        ringback.addSegment( 200, 0 );
        ringback.addSegment( 400, 400, 450 );
        ringback.addSegment( 2000, 0 );
        ringback.setDescription( "A ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );

        euro_ringback = new ToneSequence( parent );
        euro_ringback.addSegment( 1000, 425 );
        euro_ringback.addSegment( 4000, 0 );
        euro_ringback.setDescription( "A ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );

    }
}
