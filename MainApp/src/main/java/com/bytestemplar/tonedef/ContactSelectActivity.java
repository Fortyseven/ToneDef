/*******************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2016 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 ******************************************************************************/

package com.bytestemplar.tonedef;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bytestemplar.tonedef.utils.UICustom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactSelectActivity extends ListActivity
{
    public static final int RESULT_CODE_CONTACT_SELECT_OK = 1000;

    private List<String>         _tels;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        UICustom.init( getApplicationContext() );

        String vcard = receiveVCard( getIntent() );
        _tels = parseVCardTels( vcard );

        Log.i( "BT", "V-card received: " + _tels );

        // Don't even show the dialog if there's only one entry; pass it back immediately
        if ( _tels.size() == 1 ) {
            returnNumber( _tels.get( 0 ) );
            finish();
        }

        if ( _tels.size() == 0 ) {
            Toast.makeText( this, "No numbers associated with contact.", Toast.LENGTH_LONG ).show();
            finish();
        }


        setContentView( R.layout.contact_select_dialog );

        ArrayAdapter<String> _list_adapter = new ArrayAdapter<>( this, R.layout.single_list_item, _tels );
        setListAdapter( _list_adapter );
    }

    @Override
    protected void onListItemClick( ListView l, View v, int position, long id )
    {
        super.onListItemClick( l, v, position, id );
        returnNumber( _tels.get( position ) );
    }


    private void returnNumber( String number )
    {
        Bundle result = new Bundle();
        result.putString( "number", number );

        Intent i = new Intent();
        i.putExtras( result );

        setResult( RESULT_CODE_CONTACT_SELECT_OK, i );
        finish();
    }

    /* Processes a text/x-vcard content stream; extracts vcard data */
    /* Credit: http://stackoverflow.com/a/12771561/14615 */

    private String receiveVCard( Intent i )
    {
        Uri             uri    = (Uri) i.getExtras().get( Intent.EXTRA_STREAM );
        ContentResolver cr     = getContentResolver();
        InputStream     stream = null;

        try {
            stream = cr.openInputStream( uri );
        }
        catch ( FileNotFoundException e ) {
            Log.e( "BT", "Error opening vcard: " + e.getMessage() );
        }

        StringBuffer file_content = new StringBuffer( "" );
        int          ch;

        try {
            while ( ( ch = stream.read() ) != -1 ) {
                file_content.append( (char) ch );
            }
        }
        catch ( IOException e ) {
            Log.e( "BT", "Error reading vcard: " + e.getMessage() );
        }

        return new String( file_content );
    }

    /* Parses a string containing vcard data; extracts telephone items into List */
    private List<String> parseVCardTels( String vcard )
    {
        List<String> tels = new ArrayList();

        for ( String line : vcard.split( "\r\n" ) ) {
            if ( line.startsWith( "TEL" ) ) {
                tels.add( line.substring( line.indexOf( ":" ) + 1 ) );
            }
        }
        return tels;
    }
}
