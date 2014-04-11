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

package com.bytestemplar.tonedef;

import android.os.Bundle;

import com.bytestemplar.tonedef.tones.ToneBankRedBox;
import com.bytestemplar.tonedef.touchpad.ToneButtonList;
import com.bytestemplar.tonedef.touchpad.TouchPadActivity;
import com.bytestemplar.tonedef.utils.Alert;

public class RedBoxActivity extends TouchPadActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setToneBank( new ToneBankRedBox( this ) );
        setTouchPadLayoutId( R.layout.touchpad_redbox );

        try {
            setup( DISABLE_DIALINGSTRING, DISABLE_MENU );
        }
        catch ( Exception e ) {
            Alert.show( this, "Error setting up TouchPadActivity:" + e.toString() );
        }
    }

    @Override
    public void defineToneButtons( ToneButtonList buttons )
    {
        buttons.add( R.id.btnRB5cents, -1, '1' ); // 05c
        buttons.add( R.id.btnRB10cents, -1, '2' ); // 10c
        buttons.add( R.id.btnRB25cents, -1, '3' ); // 25c

        buttons.add( R.id.btnRB10p, -1, '4' ); // 10p
        buttons.add( R.id.btnRB50p, -1, '5' ); // 50p
    }
}
