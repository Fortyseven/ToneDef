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

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.utils.UICustom;

import java.util.ArrayList;

public class InternationalActivity extends FragmentActivity implements CountryListFragment.OnCountrySelectedListener
{

    private final String DIALTONE_DESC = "A dial tone is a telephony signal used to indicate that the telephone exchange is " +
                                         "working, has recognized an off-hook, and is ready to accept a call.";

    private final String RINGBACK_DESC = "A ringback tone is an audible indication that is heard on the telephone line by " +
                                         "the caller while the phone they are calling is being rung. It is normally a repeated " +
                                         "tone, designed to assure the calling party that the called party's line is ringing, " +
                                         "although the ring-back tone may be out of sync with the ringing signal.";

    private ArrayList<CountryTones> _country_tones;
    private CountryListAdapter      _country_list_adapter;
    private Typeface                _ttf_dxb;

    /******************************************************************/
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        this._ttf_dxb = Typeface.createFromAsset( this.getAssets(), UICustom.TYPEFACE_FILENAME );

        buildCountrySequences();

        setContentView( R.layout.international );

        // Single view UI
        if ( findViewById( R.id.frag_container ) != null ) {
            if ( savedInstanceState != null ) {
                return;
            }

            CountryListFragment country_list_frag = new CountryListFragment();

            country_list_frag.setArguments( getIntent().getExtras() );

            getSupportFragmentManager()
                    .beginTransaction()
                    .add( R.id.frag_container, country_list_frag )
                    .commit();
        }
        else {
            //CountryListFragment country_list_frag = (CountryListFragment) getSupportFragmentManager().findFragmentByTag( "country_list_frag" );
        }
    }

    /******************************************************************/
    public Typeface getCustomTypeface()
    {
        return _ttf_dxb;
    }

    /******************************************************************/
    public CountryListAdapter getCountryListAdapter()
    {
        return _country_list_adapter;
    }

    /******************************************************************/
    public CountryTones getCountryAtPosition( int position )
    {
        return _country_tones.get( position );
    }

    /******************************************************************/
    @Override
    public void onCountrySelected( int position )
    {
        ButtonsFragment buttons_fragment = (ButtonsFragment) getSupportFragmentManager().findFragmentById( R.id.frag_buttons );

        if ( buttons_fragment != null ) {
            // multipane
            buttons_fragment.updateButtons( position );
        }
        else {
            // one pane
            buttons_fragment = new ButtonsFragment();
            Bundle args = new Bundle();
            args.putInt( ButtonsFragment.ARG_POSITION, position );
            buttons_fragment.setArguments( args );

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace( R.id.frag_container, buttons_fragment );
            trans.addToBackStack( null );

            trans.commit();
        }
    }

    /******************************************************************/
    private void buildCountrySequences()
    {
        _country_tones = new ArrayList<>();


        _country_tones.add( buildTonesGermany() );
        _country_tones.add( buildTonesItaly() );
        _country_tones.add( buildTonesJapan() );
        _country_tones.add( buildTonesUnitedStates() );

        _country_list_adapter = new CountryListAdapter( _country_tones, this );

    }

    /******************************************************************/
    /******************************************************************/
    /******************************************************************/

    // Much thanks to World Tone Database: http://www.3amsystems.com/World_Tone_Database
    private CountryTones buildTonesUnitedStates()
    {
        CountryTones country = new CountryTones( "United States" );

        ToneSequence ts = new ToneSequence( this );
        ts.addSegment( 250, 350, 440 )
          .setDescription( DIALTONE_DESC );
        country.addSequence( "Dialtone", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 2000, 440, 480 )
          .addSegment( 4000, 0 )
          .setDescription( DIALTONE_DESC );
        country.addSequence( "Ringback", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 500, 480, 620 )
          .addSegment( 500, 0 )
          .setDescription( "" );
        country.addSequence( "Busy", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 380, 914 )
          .addSegment( 276, 1429 )
          .addSegment( 380, 1777 )
          .addSegment( 2000, 0 )
          .setDescription( "" );
        country.addSequence( "SIS - Ineffective", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 276, 914 )
          .addSegment( 276, 1371 )
          .addSegment( 380, 1777 )
          .addSegment( 2000, 0 )
          .setDescription( "" );
        country.addSequence( "SIS - Intercept", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 380, 985 )
          .addSegment( 380, 1429 )
          .addSegment( 380, 1777 )
          .addSegment( 2000, 0 )
          .setDescription( "" );
        country.addSequence( "SIS - No Circuit", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 100, 1400, 2060, 2450, 2600 )
          .addSegment( 100, 0 )
          .setDescription( "" );
        country.addSequence( "Off Hook", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 100, 350, 440 )
          .addSegment( 100, 0 )
          .addSegment( 100, 350, 440 )
          .addSegment( 100, 0 )
          .addSegment( 100, 350, 440 )
          .addSegment( 100, 0 )
          .addSegment( 6000, 350, 440 )
          .setDescription( "" );
        country.addSequence( "Recall Tone", ts );

        country.setFlagDrawable( R.drawable.flag_usa );

        return country;
    }

    /******************************************************************/
    private final int DE_FREQ = 425;

    private CountryTones buildTonesGermany()
    {
        CountryTones country = new CountryTones( "Germany" );

        ToneSequence ts = new ToneSequence( this );
        ts.addSegment( 250, DE_FREQ )
          .setDescription( DIALTONE_DESC );

        country.addSequence( "Dialtone", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 1000, DE_FREQ )
          .addSegment( 4000, 0 )
          .setDescription( RINGBACK_DESC );
        country.addSequence( "Ringback", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 500, DE_FREQ )
          .addSegment( 500, 0 )
          .setDescription( "" );
        country.addSequence( "Busy", ts );

        country.setFlagDrawable( R.drawable.flag_germany );

        return country;
    }

    /******************************************************************/
    private final int JP_DIALTONE_FREQ  = 400;
    private final int JP_RINGBACK_FREQ1 = 384;
    private final int JP_RINGBACK_FREQ2 = 416;
    private final int JP_BUSY_FREQ      = 400;

    private CountryTones buildTonesJapan()
    {
        CountryTones country = new CountryTones( "Japan" );

        ToneSequence ts = new ToneSequence( this );
        ts.addSegment( 250, JP_DIALTONE_FREQ )
          .setDescription( DIALTONE_DESC );

        country.addSequence( "Dialtone", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 1000, JP_RINGBACK_FREQ1, JP_RINGBACK_FREQ2 )
          .addSegment( 2000, 0 )
          .setDescription( RINGBACK_DESC );

        country.addSequence( "Ringback", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 500, JP_BUSY_FREQ )
          .addSegment( 500, 0 )
          .setDescription( "" );

        country.addSequence( "Busy", ts );

        country.setFlagDrawable( R.drawable.flag_japan );
        return country;
    }

    /******************************************************************/
    private final int ITALY_FREQ = 425;

    private CountryTones buildTonesItaly()
    {
        CountryTones country = new CountryTones( "Italy" );

        ToneSequence ts = new ToneSequence( this );
        ts.addSegment( 1000, ITALY_FREQ )
          .addSegment( 4000, 0 )
          .setDescription( RINGBACK_DESC );

        country.addSequence( "Ringback", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 200, ITALY_FREQ )
          .addSegment( 200, 0 )
          .addSegment( 600, ITALY_FREQ )
          .addSegment( 1000, 0 )
          .setDescription( DIALTONE_DESC );
        country.addSequence( "Dialtone", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 500, ITALY_FREQ )
          .addSegment( 500, 0 )
          .setDescription( "" );
        country.addSequence( "Busy", ts );

        country.setFlagDrawable( R.drawable.flag_italy );

        return country;
    }
}
