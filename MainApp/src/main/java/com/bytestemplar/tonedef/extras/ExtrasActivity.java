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

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.bytestemplar.tonedef.utils.UICustom;

@SuppressWarnings("deprecation")
public class ExtrasActivity extends TabActivity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        TabHost th = getTabHost();
        TabSpec tab;
        th.setup();

        // Initialize a TabSpec for each tab and add it to the TabHost

        tab = th.newTabSpec( "usa" );
        tab.setContent( new Intent( this, USTonesActivity.class ) );
        tab.setIndicator( "USA" );
        th.addTab( tab );

        tab = th.newTabSpec( "uk" );
        tab.setContent( new Intent( this, UKTonesActivity.class ) );
        tab.setIndicator( "UK" );
        th.addTab( tab );

        tab = th.newTabSpec( "other" );
        tab.setContent( new Intent( this, OtherTonesActivity.class ) );
        tab.setIndicator( "Other" );
        th.addTab( tab );

        th.setCurrentTab( 0 );
        UICustom.getInstance().updateActivity( this );
    }
}
