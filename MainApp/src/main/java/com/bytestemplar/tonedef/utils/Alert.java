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

package com.bytestemplar.tonedef.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * ToneDef - com.bytestemplar.tonedef_core.utils
 * Created by Fortyseven on 4/6/2014.
 */
public class Alert
{
    public interface OnClick
    {

        public abstract void action( Context context );

    }

    /**
     * ********************************************************
     *
     * @param context
     *         Context handle
     * @param message
     *         Message for show box
     */
    public static void show( Activity context, String message )
    {
        show( context, "", message );
    }

    public static void show( Activity context, String title, String message )
    {
        show( context, title, message, -1 );
    }

    public static void show( Activity context, String title, String message, int icon_resource )
    {
        show( context, title, message, icon_resource, null );
    }

    /**
     * @param context
     *         Context handle
     * @param title
     *         Title for top of show box (optional)
     * @param message
     *         Message for show box
     * @param on_close
     *         Callback to perform when box is dismissed
     */
    public static void show( final Activity context, final String title, final String message, final int icon_resource, final OnClick on_close )
    {
        context.runOnUiThread( new Runnable()
        {
            public void run()
            {
                int my_icon_resource = icon_resource;
                if ( icon_resource == -1 ) {
                    my_icon_resource = android.R.drawable.ic_dialog_info;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder( context );
                builder.setMessage( message ).setIcon( my_icon_resource ).setTitle( title ).setPositiveButton( "Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int id )
                    {
                        dialog.dismiss();
                        if ( on_close != null ) {
                            on_close.action( context );
                        }
                    }
                } ).create().show();

            }
        } );
    }

    public static void error( Activity context, String message )
    {
        show( context, "Whoops!", message );
    }

    public static void error( Activity context, String title, String message )
    {
        Log.e( "BT", "ERROR: " + message );
        show( context, title, message, android.R.drawable.ic_dialog_alert );
    }

}
