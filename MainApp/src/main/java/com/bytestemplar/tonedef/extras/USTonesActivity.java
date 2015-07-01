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

package com.bytestemplar.tonedef.extras;

import android.os.Bundle;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.TonePanelActivity;
import com.bytestemplar.tonedef.tones.USTones;

/**
 * Various tones from around the United States.
 */
public class USTonesActivity extends TonePanelActivity
{

    USTones us_tones;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.us_tones );

        us_tones = new USTones( this );

        defineButton( R.id.btnUSBusy, R.id.btnUSBusyAbout, us_tones.busy );
        defineButton( R.id.btnUSOffHook, R.id.btnUSOffHookAbout, us_tones.offhook );
        defineButton( R.id.btnUSCTone, R.id.btnUSCToneAbout, us_tones.ctone );
        defineButton( R.id.btnUSDialtone, R.id.btnUSDialtoneAbout, us_tones.dialtone );
        defineButton( R.id.btnUSRingback, R.id.btnUSRingbackAbout, us_tones.ringback );

        setTitleText( us_tones.title );

        setup();
    }
}