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

package com.bytestemplar.tonedef.extras;

import android.os.Bundle;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.TonePanelActivity;
import com.bytestemplar.tonedef.tones.UKTones;

/**
 * Various tones from the United Kingdom.
 */
public class UKTonesActivity extends TonePanelActivity
{

    UKTones uk_tones;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.uk_tones );

        uk_tones = new UKTones( this );

        defineButton( R.id.btnUKDialtone, R.id.btnUKDialtoneAbout, uk_tones.dialtone );
        defineButton( R.id.btnUKRingback, R.id.btnUKRingbackAbout, uk_tones.ringback );
        defineButton( R.id.btnUKEuroRingback, R.id.btnUKEuroRingbackAbout, uk_tones.euro_ringback );

        setTitleText( uk_tones.title );

        setup();
    }
}