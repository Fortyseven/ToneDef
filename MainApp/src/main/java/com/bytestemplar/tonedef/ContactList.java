/*******************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2014 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 ******************************************************************************/

package com.bytestemplar.tonedef;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.bytestemplar.tonedef.utils.Alert;

public class ContactList
{
    public static final int RESULT_CODE = 1000;

    public static void fetch( Activity context )
    {
        Intent i = new Intent( Intent.ACTION_PICK, Contacts.CONTENT_URI );
        context.startActivityForResult( i, RESULT_CODE );
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
                    int has = c.getInt( c.getColumnIndexOrThrow( ( ContactsContract.PhoneLookup.HAS_PHONE_NUMBER ) ) );
                    if ( has != 0 ) {
                        Cursor phones = context.getContentResolver()
                                               .query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null );
                        if ( phones != null ) {
                            while ( phones.moveToNext() ) {
                                phone = phones.getString( phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER ) );
                            }
                            phones.close();
                        }

                        result = phone;
                    }
                    else {
                        Alert.show( context, context.getResources().getString( R.string.no_contact ) );
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
