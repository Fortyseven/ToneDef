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

package com.bytestemplar.tonedef;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.bytestemplar.tonedef.extras.ExtrasActivity;
import com.bytestemplar.tonedef.utils.UICustom;

public class MainActivity extends Activity
{
    private static final String PREF_LASTVER = "lastver";


    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        UICustom.init( this );
        UICustom.getInstance().updateActivity( this );

        showWhatsNew();
    }

    public void showWhatsNew()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );

        // Check for which version was last run, and if we're newer than
        // the previous version, we're going to display the About activity
        // that has the changelog and such.

        if ( prefs.contains( PREF_LASTVER ) ) {
            if ( prefs.getString( PREF_LASTVER, null ).contentEquals( getString( R.string.version ) ) ) {
                // Already showed the box (version last shown == // current version)
                return;
            }
        }

        // Set current version as the version last seen, so next time we won't
        // pester the user with this.
        prefs.edit().putString( PREF_LASTVER, getString( R.string.version ) ).commit();

        startActivity( new Intent( this, AboutActivity.class ) );
    }

    public void clickDTMF( View view )
    {
        startActivity( new Intent( view.getContext(), DTMFActivity.class ) );
    }

    public void clickBluebox( View view )
    {
        startActivity( new Intent( view.getContext(), BlueBoxActivity.class ) );
    }

    public void clickRedbox( View view )
    {
        startActivity( new Intent( view.getContext(), RedBoxActivity.class ) );
    }

    public void clickAbout( View view )
    {
        startActivity( new Intent( view.getContext(), AboutActivity.class ) );
    }

    public void clickConfig( View view )
    {
        startActivity( new Intent( view.getContext(), ConfigActivity.class ) );
    }

    public void clickExtras( View view )
    {
        startActivity( new Intent( view.getContext(), ExtrasActivity.class ) );
    }
}