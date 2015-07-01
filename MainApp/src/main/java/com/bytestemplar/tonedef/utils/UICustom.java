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

package com.bytestemplar.tonedef.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * ToneDef - com.bytestemplar.tonedef.utils
 * Created by Fortyseven on 4/9/2014.
 */
public class UICustom
{
    public static final String   TYPEFACE_FILENAME = "c64.ttf";
    public static       UICustom instance          = null;
    private static      Typeface mFont             = null;


    static public UICustom getInstance()
    {
        if ( UICustom.instance == null ) {
            throw new RuntimeException( "UICustom was not initialized" );
        }
        return instance;
    }

    static public void init( Context context )
    {
        instance = new UICustom( context );
    }

    public UICustom( Context context )
    {
        mFont = Typeface.createFromAsset( context.getAssets(), TYPEFACE_FILENAME );
    }

    public void updateActivity( Activity act )
    {
        ViewGroup root = ( (ViewGroup) act.findViewById( android.R.id.content ) );

        updateViewHierarchy( root );
    }

    private void updateViewHierarchy( ViewGroup root )
    {
        for ( int c = 0; c < root.getChildCount(); c++ ) {
            View view = root.getChildAt( c );

            if ( ( view instanceof LinearLayout ) ||
                 ( view instanceof ScrollView ) ||
                 ( view instanceof TabHost ) ||
                 ( view instanceof FrameLayout ) )
            {
                updateViewHierarchy( (ViewGroup) view );
            }
            else {
                updateView( view );
            }
        }
    }

    public void updateView( View btn )
    {
        btn.setSoundEffectsEnabled( false );

        if ( btn instanceof EditText ) {
            ( (EditText) ( btn ) ).setTypeface( mFont );
        }
        else if ( btn instanceof TextView ) {
            ( (TextView) ( btn ) ).setTypeface( mFont );
        }
        else if ( btn instanceof Button ) {
            ( (Button) ( btn ) ).setTypeface( mFont );
        }
    }
}
