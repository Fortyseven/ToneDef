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

package com.bytestemplar.tonedef.touchpad;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;

/**
 *
 */
public class ToneButtonList
{
    public ArrayList<ButtonDefinition> buttons = null;
    private final Activity parent;

    /**
     *
     * @param parent_activity The activity containing a layout housing the buttons to be hooked.
     */
    public ToneButtonList( Activity parent_activity )
    {
        this.parent = parent_activity;
        buttons = new ArrayList<ButtonDefinition>();
    }

    /**
     * Adds a button _definition to the list. The View ID provided will be onClick hooked to
     * generate the,,, wait, fuck.
     *
     * @param view_id The ID of the view that will catch the onClick event to trigger this tone
     * @param icon_resource Resource ID of a drawable to be attached to the button (-1 for none)
     * @param c Unique character this tone represents for use in dialing strings. (The #3 on the keypad etc.)
     */
    public void add( int view_id, int icon_resource, char c )
    {
        View v = parent.findViewById( view_id );

        if ( v == null ) {
            throw new RuntimeException( "Could not find view when adding to ToneButtonList" );
        }
        buttons.add( new ButtonDefinition( v, icon_resource, c ) );
    }
}
