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

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.utils.UICustom;

public class InternationalActivity extends FragmentActivity implements CountryListFragment.OnCountrySelectedListener
{
    public static final String COUNTRY_TAG = "CountryFrag";
    public static final String BUTTON_TAG  = "ButtonFrag";

    private Typeface _ttf_dxb;
    private boolean  _is_dual_pane;

    /******************************************************************/
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        this._ttf_dxb = Typeface.createFromAsset( this.getAssets(), UICustom.TYPEFACE_FILENAME );

        setContentView( R.layout.international );

        //Log.d( "BT", "OnCreate ---" );

        _is_dual_pane = true;

        // Single view UI
        if ( findViewById( R.id.frag_single_container ) != null ) {
            _is_dual_pane = false;

            CountryListFragment frag;

            frag = new CountryListFragment();

            frag.setArguments( getIntent().getExtras() );

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.frag_single_container, frag, COUNTRY_TAG )
                    .commit();
        }
    }

    /******************************************************************/
    public Typeface getCustomTypeface()
    {
        return _ttf_dxb;
    }


    /******************************************************************/
    @Override
    public void onCountrySelected( int position )
    {
        if ( !_is_dual_pane ) {

            Intent intent = new Intent( this, ButtonActivity.class );
            intent.putExtra( ButtonsFragment.ARG_POSITION, position );
            startActivity( intent );
            return;
        }

        //Log.d( "BT", "onCountrySelected --- multi; updating buttons in button fragment" );
        // multipane
        ButtonsFragment buttons_fragment = (ButtonsFragment) getSupportFragmentManager().findFragmentById( R.id.frag_buttons );

        if ( buttons_fragment != null ) {
            buttons_fragment.updateButtons( position );
            return;
        }
    }

    /******************************************************************/
    private void buildNewButtonFragment( int position )
    {
        ButtonsFragment buttons_fragment = new ButtonsFragment();
        Bundle          args             = new Bundle();

        args.putInt( ButtonsFragment.ARG_POSITION, position );
        buttons_fragment.setArguments( args );

        getSupportFragmentManager().beginTransaction()
                                   .replace( R.id.frag_single_container, buttons_fragment, BUTTON_TAG )
                                   .addToBackStack( "POTATO" )
                                   .commit();
    }

    /******************************************************************/
    @Override
    public void onBackPressed()
    {
        if ( getSupportFragmentManager().getBackStackEntryCount() == 1 ) {
            finish();
        }
        else {
            super.onBackPressed();
        }
    }
}
