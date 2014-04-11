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

import com.bytestemplar.tonedef.tones.ToneBankBlueBox;
import com.bytestemplar.tonedef.touchpad.ToneButtonList;
import com.bytestemplar.tonedef.touchpad.TouchPadActivity;
import com.bytestemplar.tonedef.utils.Alert;

public class BlueBoxActivity extends TouchPadActivity
{
    private static final String PREF_BLU_MARK  = "bluebox_digitdur";
    private static final String PREF_BLU_SPACE = "bluebox_dialdelay";
    private static final String PREF_BLU_DELAY = "bluebox_sdelay";
    private static final String PREF_WHITE     = "invert_text_color";

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setPreferenceIdentifiers( PREF_BLU_MARK, PREF_BLU_SPACE, PREF_BLU_DELAY );
        setToneBank( new ToneBankBlueBox( this ) );
        setTouchPadLayoutId( R.layout.touchpad_bluebox );

        if ( mPrefs.contains( PREF_WHITE ) ) {
            invertKeypadAssets();
        }

        //setBackgroundDrawable( R.drawable.grad_blue );
        
        try {
            setup( ENABLE_DIALINGSTRING, ENABLE_MENU );
        }
        catch ( Exception e ) {
            Alert.show( this, "Error setting up TouchPadActivity:" + e.toString() );
        }
    }

    private void invertKeypadAssets()
    {
        //( (ImageButton) findViewById( R.id.blus ) ).setImageResource( R.drawable.idtmfpound );
    }

    @Override
    public void defineToneButtons( ToneButtonList buttons )
    {
        buttons.add( R.id.blu1, -1, '1' );
        buttons.add( R.id.blu2, -1, '2' );
        buttons.add( R.id.blu3, -1, '3' );
        buttons.add( R.id.blu4, -1, '4' );
        buttons.add( R.id.blu5, -1, '5' );
        buttons.add( R.id.blu6, -1, '6' );
        buttons.add( R.id.blu7, -1, '7' );
        buttons.add( R.id.blu8, -1, '8' );
        buttons.add( R.id.blu9, -1, '9' );

        buttons.add( R.id.bluk, -1, 'K' );
        buttons.add( R.id.blu0, -1, '0' );
        buttons.add( R.id.blus, -1, 'S' );

        buttons.add( R.id.blu2600, -1, 'X' );
    }
}
