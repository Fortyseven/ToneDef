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
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class CountryListFragment extends ListFragment
{
    public interface OnCountrySelectedListener
    {
        public void onCountrySelected( int position );
    }

    OnCountrySelectedListener mCallback;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        InternationalActivity.CountryListAdapter foo = ( (InternationalActivity) ( getActivity() ) ).getCountryListAdapter();
        Log.d( "BT", "Was I null? " + foo );

        setListAdapter( foo );
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
}
