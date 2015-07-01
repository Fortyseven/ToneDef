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

package com.bytestemplar.tonedef;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bytestemplar.tonedef.utils.UICustom;

public class AboutActivity extends Activity
{
//    private Typeface ttf_dxb;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.about );

        UICustom.getInstance().updateActivity( this );
    }

    public void clickGitHub( View view )
    {
        startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( "https://github.com/Fortyseven/ToneDef" ) ) );
    }

    public void clickClose( View view )
    {
        finish();
    }
}
