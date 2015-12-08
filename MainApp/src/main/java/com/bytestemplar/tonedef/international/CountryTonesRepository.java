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

import android.app.Activity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.ArrayList;

public class CountryTonesRepository
{
    private static CountryTonesRepository instance       = null;
    private        Activity               _ownerActivity = null;
    private ArrayList<CountryTones> _country_tones;
    private CountryListAdapter      _country_list_adapter;

    /******************************************************************/
    public static CountryTonesRepository getInstance()
    {
        if ( instance == null ) {
            throw new IllegalStateException( "Must call getInstance at least once with a parent Activity" );
        }
        return instance;
    }

    /******************************************************************/
    public static CountryTonesRepository getInstance( Activity owner )
    {
        if ( CountryTonesRepository.instance == null ) {
            CountryTonesRepository.instance = new CountryTonesRepository( owner );
        }
        return instance;
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
    public CountryTonesRepository( Activity owner )
    {
        _ownerActivity = owner;
        buildCountrySequences();
    }

    /******************************************************************/
    private void buildCountrySequences()
    {
        _country_tones = new ArrayList<>();

        _country_tones.add( buildTonesGermany() );
        _country_tones.add( buildTonesItaly() );
        _country_tones.add( buildTonesJapan() );
        _country_tones.add( buildTonesUnitedStates() );

        _country_list_adapter = new CountryListAdapter( _country_tones );

    }

    /******************************************************************/
    /******************************************************************/
    /******************************************************************/

    // Much thanks to World Tone Database: http://www.3amsystems.com/World_Tone_Database
    private CountryTones buildTonesUnitedStates()
    {
        CountryTones country = new CountryTones( _ownerActivity.getString( R.string.country_usa ) );

        ToneSequence ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 250, 350, 440 )
          .setDescription( _ownerActivity.getString( R.string.desc_dialtone ) );
        country.addSequence( _ownerActivity.getString( R.string.dialtone ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 2000, 440, 480 )
                .addSegment( 4000, 0 )
                        //.setDescription( _ownerActivity.getString( R.string.desc_ringback ) );
                .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.ringback ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 500, 480, 620 )
          .addSegment( 500, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.busy ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 380, 914 )
          .addSegment( 276, 1429 )
          .addSegment( 380, 1777 )
          .addSegment( 2000, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.sis_ineffective ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 276, 914 )
          .addSegment( 276, 1371 )
          .addSegment( 380, 1777 )
          .addSegment( 2000, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.sis_intercept ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 380, 985 )
          .addSegment( 380, 1429 )
          .addSegment( 380, 1777 )
          .addSegment( 2000, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.sis_nocircuit ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 100, 1400, 2060, 2450, 2600 )
          .addSegment( 100, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.off_hook ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 100, 350, 440 )
          .addSegment( 100, 0 )
          .addSegment( 100, 350, 440 )
          .addSegment( 100, 0 )
          .addSegment( 100, 350, 440 )
          .addSegment( 100, 0 )
          .addSegment( 6000, 350, 440 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.recall_tone ), ts );

        country.setFlagDrawable( R.drawable.flag_usa );

        return country;
    }

    /******************************************************************/
    private final int DE_FREQ = 425;

    private CountryTones buildTonesGermany()
    {
        CountryTones country = new CountryTones( _ownerActivity.getString( R.string.country_germany ) );

        ToneSequence ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 250, DE_FREQ )
          .setDescription( _ownerActivity.getString( R.string.desc_dialtone ) );

        country.addSequence( _ownerActivity.getString( R.string.dialtone ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 1000, DE_FREQ )
          .addSegment( 4000, 0 )
          .setDescription( _ownerActivity.getString( R.string.desc_ringback ) );
        country.addSequence( _ownerActivity.getString( R.string.ringback ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 500, DE_FREQ )
          .addSegment( 500, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.busy ), ts );

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
        CountryTones country = new CountryTones( _ownerActivity.getString( R.string.country_japan ) );

        ToneSequence ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 250, JP_DIALTONE_FREQ )
          .setDescription( _ownerActivity.getString( R.string.desc_dialtone ) );

        country.addSequence( _ownerActivity.getString( R.string.dialtone ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 1000, JP_RINGBACK_FREQ1, JP_RINGBACK_FREQ2 )
          .addSegment( 2000, 0 )
          .setDescription( _ownerActivity.getString( R.string.desc_ringback ) );

        country.addSequence( _ownerActivity.getString( R.string.ringback ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 500, JP_BUSY_FREQ )
          .addSegment( 500, 0 )
          .setDescription( "" );

        country.addSequence( _ownerActivity.getString( R.string.busy ), ts );

        country.setFlagDrawable( R.drawable.flag_japan );
        return country;
    }

    /******************************************************************/
    private final int ITALY_FREQ = 425;

    private CountryTones buildTonesItaly()
    {
        CountryTones country = new CountryTones( _ownerActivity.getString( R.string.country_italy ) );

        ToneSequence ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 1000, ITALY_FREQ )
          .addSegment( 4000, 0 )
          .setDescription( _ownerActivity.getString( R.string.desc_ringback ) );

        country.addSequence( _ownerActivity.getString( R.string.ringback ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 200, ITALY_FREQ )
          .addSegment( 200, 0 )
          .addSegment( 600, ITALY_FREQ )
          .addSegment( 1000, 0 )
          .setDescription( _ownerActivity.getString( R.string.desc_dialtone ) );
        country.addSequence( _ownerActivity.getString( R.string.dialtone ), ts );

        ts = new ToneSequence( _ownerActivity );
        ts.addSegment( 500, ITALY_FREQ )
          .addSegment( 500, 0 )
          .setDescription( "" );
        country.addSequence( _ownerActivity.getString( R.string.busy ), ts );

        country.setFlagDrawable( R.drawable.flag_italy );

        return country;
    }
}
