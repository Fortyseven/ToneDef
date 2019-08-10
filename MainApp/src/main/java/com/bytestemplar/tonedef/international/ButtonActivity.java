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

package com.bytestemplar.tonedef.international;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

public class ButtonActivity extends FragmentActivity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        // single-pane
        if ( savedInstanceState == null ) {
            ButtonsFragment buttons_fragment = new ButtonsFragment();

            buttons_fragment.setArguments( getIntent().getExtras() );

            getSupportFragmentManager().beginTransaction()
                                       .replace( android.R.id.content, buttons_fragment, InternationalActivity.BUTTON_TAG )
                                       .commit();
        }
        else {
            // TODO: Need to revisit this; kicks us back to country list on orientation change
            finish();
        }
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState )
    {
        super.onRestoreInstanceState( savedInstanceState );
    }
}
