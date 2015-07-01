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

import com.bytestemplar.tonedef.gen.ToneSequence;

public class USTones
{
    public final ToneSequence busy;
    public final ToneSequence offhook;
    public final ToneSequence ctone;
    public final ToneSequence dialtone;
    public final ToneSequence ringback;

    public String title = "US Telephony";
    private final Activity parent;

    public USTones( Activity parent )
    {

        this.parent = parent;
        busy = new ToneSequence( parent );
        busy.addSegment( 500, 480, 620 );
        busy.addSegment( 500, 0 );
        busy.setDescription( "A busy signal is a signal which indicates that the telephone number someone is trying to call cannot be reached because the phone is otherwise engaged, or because the circuits are busy, making the call impossible to complete." );

        offhook = new ToneSequence( parent );
        offhook.addSegment( 100, 1440, 2060, 2450, 2600 );
        offhook.addSegment( 100, 0 );
        offhook.setDescription( "The off-hook tone is a telephony signal used to show a user that the telephone has been left off-hook for an extended period, effectively disabling the telephone line." );

        // http://www.crypto.com/papers/wiretapping/
        ctone = new ToneSequence( parent );
        ctone.addSegment( 250, 852, 1633 );
        ctone.setDescription( "This is simply the DTMF 'C' tone, thought to cause some wiretap systems to disengage. Your milage may vary. [http://www.crypto.com/papers/wiretapping/]" );

        dialtone = new ToneSequence( parent );
        dialtone.addSegment( 250, 350, 440 );
        dialtone.setDescription( "A dial tone is a telephony signal used to indicate that the telephone exchange is working, has recognized an off-hook, and is ready to accept a call." );

        ringback = new ToneSequence( parent );
        ringback.addSegment( 2000, 440, 480 );
        ringback.addSegment( 4000, 0 );
        ringback.setDescription( "A _ringback tone is an audible indication that is heard on the telephone line by the caller while the phone they are calling is being rung. It is normally a repeated tone, designed to assure the calling party that the called party's line is ringing, although the ring-back tone may be out of sync with the ringing signal." );
    }
}
