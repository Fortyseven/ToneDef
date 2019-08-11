/**
 * ****************************************************************************
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
 * ****************************************************************************
 */

package com.bytestemplar.tonedef;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.bytestemplar.tonedef.utils.Alert;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ContactList
{
    public static final int PERM_CONTACT_REQUEST = 1000;
    public static final int RESULT_CODE = 1000;

    public static void fetch( Activity context )
    {
        // Check if we have permission to do this; ask otherwise
        if ( ContextCompat.checkSelfPermission( context, Manifest.permission.READ_CONTACTS ) == PackageManager.PERMISSION_GRANTED ) {
            Intent i = new Intent( Intent.ACTION_PICK, Contacts.CONTENT_URI );
            context.startActivityForResult( i, RESULT_CODE );
        }
        else {
            ActivityCompat.requestPermissions( context, new String[]{Manifest.permission.READ_CONTACTS}, PERM_CONTACT_REQUEST );
        }
    }


    public static String parseResponse( Activity context, int requestCode, int resultCode, Intent data )
    {
        String result = null;

        if ( ( requestCode == RESULT_CODE ) && ( resultCode == Activity.RESULT_OK ) ) {

            Uri contactData = data.getData();
            Cursor c = context.managedQuery( contactData, null, null, null, null );
            String phone = "";

            if ( c.moveToFirst() ) {
                String contactId = c.getString( c.getColumnIndex( BaseColumns._ID ) );
                try {
                    int
                            has =
                            c.getInt( c.getColumnIndexOrThrow( ( ContactsContract.PhoneLookup.HAS_PHONE_NUMBER ) ) );
                    if ( has != 0 ) {
                        Cursor phones = context.getContentResolver()
                                               .query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                       null,
                                                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                                                       null,
                                                       null );
                        if ( phones != null ) {
                            while ( phones.moveToNext() ) {
                                phone =
                                        phones.getString( phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER ) );
                            }
                            phones.close();
                        }

                        result = phone;
                    }
                    else {
                        Alert.show( context,
                                    context.getResources().getString( R.string.no_contact ) );
                    }

                }
                catch ( Exception e ) {
                    Alert.error( context, "ERROR: " + e.getMessage() );
                }
            }
        }

        return result;
    }
}
