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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bytestemplar.tonedef.R;

import java.util.ArrayList;

/**
 * Created by Fortyseven on 2015-12-02.
 */
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
            LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
            convertView = inflater.inflate( R.layout.countrylist_item, parent, false );
        }

        TextView tv_name = (TextView) convertView.findViewById( R.id.country_name );
        tv_name.setText( country_name );
        return convertView;
    }
}