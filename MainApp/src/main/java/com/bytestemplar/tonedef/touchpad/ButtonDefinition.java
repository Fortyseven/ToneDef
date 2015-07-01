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

import android.view.View;

public class ButtonDefinition
{
    public View btnView       = null;
    public int  icon_resource = -1;
    public char id            = 0;

    public ButtonDefinition( View view, int icon_resource, char id )
    {
        this.btnView = view;
        this.icon_resource = icon_resource;
        this.id = id;
    }
}
