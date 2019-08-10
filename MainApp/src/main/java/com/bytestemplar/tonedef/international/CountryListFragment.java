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
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class CountryListFragment extends ListFragment
{
    public interface OnCountrySelectedListener
    {
        void onCountrySelected( int position );
    }

    OnCountrySelectedListener mCallback;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        //setRetainInstance( true );

        CountryListAdapter foo = CountryTonesRepository.getInstance().getCountryListAdapter();

        setListAdapter( foo );
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState )
    {
        super.onViewCreated( view, savedInstanceState );
        getView().setBackgroundColor( Color.rgb( 221, 221, 221 ) );
    }

    @Override
    public void onAttach( Activity activity )
    {
        super.onAttach( activity );
        try {
            mCallback = (OnCountrySelectedListener) activity;
        }
        catch ( ClassCastException e ) {
            throw new ClassCastException( activity.toString() + " must implement onCountrySelected" );
        }
    }

    @Override
    public void onListItemClick( ListView l, View v, int position, long id )
    {
        mCallback.onCountrySelected( position );
        getListView().setItemChecked( position, true );
    }

    @Override
    public void onSaveInstanceState( Bundle out_state )
    {
        super.onSaveInstanceState( out_state );
    }

}
