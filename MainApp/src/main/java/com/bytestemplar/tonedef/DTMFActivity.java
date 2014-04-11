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
import android.view.View;

import com.bytestemplar.tonedef.tones.ToneBankDTMF;
import com.bytestemplar.tonedef.touchpads.ToneButtonList;
import com.bytestemplar.tonedef.utils.Alert;

public class DTMFActivity extends TouchPadActivity
{
    private static final String PREF_EXTDTMF         = "ext_dtmf";
    private static final String PREF_DTMF_SPACE      = "dtmf_dialdelay";
    private static final String PREF_DTMF_SHORTDELAY = "dtmf_sdelay";
    private static final String PREF_DTMF_MARK       = "dtmf_digitdur";

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setPreferenceIdentifiers( PREF_DTMF_MARK, PREF_DTMF_SPACE, PREF_DTMF_SHORTDELAY );

        setToneBank( new ToneBankDTMF( this ) );
        setTouchPadLayoutId( R.layout.touchpad_dtmf );

        try {
            setup( ENABLE_DIALINGSTRING, ENABLE_MENU );
        }
        catch ( Exception e ) {
            Alert.show( this, "Error setting up TouchPadActivity:" + e.toString() );
        }
        handleExtendedDTMFButtons();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        handleExtendedDTMFButtons();
    }

    private void handleExtendedDTMFButtons()
    {
        if ( mPrefs.getBoolean( PREF_EXTDTMF, false ) ) {
            findViewById( R.id.dtmfa ).setVisibility( View.VISIBLE );
            findViewById( R.id.dtmfb ).setVisibility( View.VISIBLE );
            findViewById( R.id.dtmfc ).setVisibility( View.VISIBLE );
            findViewById( R.id.dtmfd ).setVisibility( View.VISIBLE );
        }
        else {
            findViewById( R.id.dtmfa ).setVisibility( View.GONE );
            findViewById( R.id.dtmfb ).setVisibility( View.GONE );
            findViewById( R.id.dtmfc ).setVisibility( View.GONE );
            findViewById( R.id.dtmfd ).setVisibility( View.GONE );
        }
    }

    @Override
    public void defineToneButtons( ToneButtonList buttons )
    {
        buttons.add( R.id.dtmf1, -1, '1' );
        buttons.add( R.id.dtmf2, -1, '2' );
        buttons.add( R.id.dtmf3, -1, '3' );
        buttons.add( R.id.dtmfa, -1, 'A' );

        buttons.add( R.id.dtmf4, -1, '4' );
        buttons.add( R.id.dtmf5, -1, '5' );
        buttons.add( R.id.dtmf6, -1, '6' );
        buttons.add( R.id.dtmfb, -1, 'B' );

        buttons.add( R.id.dtmf7, -1, '7' );
        buttons.add( R.id.dtmf8, -1, '8' );
        buttons.add( R.id.dtmf9, -1, '9' );
        buttons.add( R.id.dtmfc, -1, 'C' );

        buttons.add( R.id.dtmf_star, -1, '*' );
        buttons.add( R.id.dtmf0, -1, '0' );
        buttons.add( R.id.dtmf_pound, -1, '#' );
        buttons.add( R.id.dtmfd, -1, 'D' );
    }
}
