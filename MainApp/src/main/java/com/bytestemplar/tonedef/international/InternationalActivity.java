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
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.ArrayList;
import java.util.HashMap;

public class InternationalActivity extends FragmentActivity implements CountryListFragment.OnCountrySelectedListener
{
    /**
     *
     */
    class CountryTones
    {
        private String                        _name;
        private HashMap<String, ToneSequence> _sequences;

        public CountryTones( String country_name )
        {
            _name = country_name;
            _sequences = new HashMap<>();
        }

        public String getName()
        {
            return _name;
        }

        public void addSequence( String name, ToneSequence sequence )
        {
            _sequences.put( name, sequence );
        }

        public HashMap<String, ToneSequence> getSequences()
        {
            return _sequences;
        }
    }

    private final int DE_DIALTONE_FREQ = 425;
    private final int DE_RINGBACK_FREQ = 425;

    private final int ITALY_FREQ = 425;

    private final int JP_RINGBACK_FREQ1 = 384;
    private final int JP_RINGBACK_FREQ2 = 416;

    private final String DIALTONE_DESC = "A dial tone is a telephony signal used to indicate that the telephone exchange is " +
                                         "working, has recognized an off-hook, and is ready to accept a call.";

    private final String RINGBACK_DESC = "A ringback tone is an audible indication that is heard on the telephone line by " +
                                         "the caller while the phone they are calling is being rung. It is normally a repeated " +
                                         "tone, designed to assure the calling party that the called party's line is ringing, " +
                                         "although the ring-back tone may be out of sync with the ringing signal.";

    private ArrayList<CountryTones> _country_tones;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        buildCountrySequences();

        setContentView( R.layout.international );

        // Single view UI
        if ( findViewById( R.id.frag_container ) != null ) {
            Log.d( "BT", "SIngle view" );
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
            Log.d( "BT", "Multi view" );
        }
    }

    public class CountryListAdapter extends BaseAdapter
    {
        ArrayList<CountryTones> _countries;

        public CountryListAdapter( ArrayList<CountryTones> countries )
        {
            Log.i( "BT", "Creating CountryListAdapter instance from " + countries );
            _countries = countries;
        }

        @Override
        public int getCount()
        {
            return _countries.size();
        }

        @Override
        public Object getItem( int position )
        {
            Log.i( "BT", "getitem " + position );
            return _countries.get( position ).getName();
        }

        @Override
        public long getItemId( int position )
        {
            return position;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent )
        {
            Log.i( "BT", "getView " + position );
            String country_name = (String) getItem( position );

            if ( convertView == null ) {
                LayoutInflater inflater = LayoutInflater.from( getBaseContext() );
                convertView = inflater.inflate( R.layout.countrylist_item, parent, false );
            }

            TextView tv_name = (TextView) convertView.findViewById( R.id.country_name );
            tv_name.setText( country_name );
            return convertView;
        }
    }


    private CountryListAdapter _country_list_adapter;

    public CountryListAdapter getCountryListAdapter()
    {
        return _country_list_adapter;
    }

    public CountryTones getCountryAtPosition( int position )
    {
        return _country_tones.get( position );
    }

    /**
     *
     */
    private void buildCountrySequences()
    {
        _country_tones = new ArrayList<CountryTones>( 0 );

        CountryTones country;
        ToneSequence ts;

        /************/

        country = new CountryTones( "Germany" );

        ts = new ToneSequence( this );
        ts.addSegment( 250, DE_DIALTONE_FREQ )
          .setDescription( DIALTONE_DESC );

        country.addSequence( "Dialtone", ts );

        _country_tones.add( country );

        /************/

        country = new CountryTones( "Japan" );

        ts = new ToneSequence( this );
        ts.addSegment( 250, 400 )
          .setDescription( DIALTONE_DESC );

        country.addSequence( "Dialtone", ts );

        ts = new ToneSequence( this );
        ts.addSegment( 1000, JP_RINGBACK_FREQ1, JP_RINGBACK_FREQ2 )
          .addSegment( 2000, 0 )
          .setDescription( RINGBACK_DESC );

        country.addSequence( "Ringback", ts );

        _country_tones.add( country );

        /************/

        country = new CountryTones( "Italy" );

        ts = new ToneSequence( this );
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

        _country_tones.add( country );

        _country_list_adapter = new CountryListAdapter( _country_tones );

    }

    @Override
    public void onCountrySelected( int position )
    {
        Log.i( "BT", "Selected: " + position );

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
}
