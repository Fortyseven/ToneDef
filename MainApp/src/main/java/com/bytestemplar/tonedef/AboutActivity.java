/**
 * ****************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 * <p/>
 * Copyright (c) 2015 Bytes Templar
 * http://BytesTemplar.com/
 * <p/>
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 * ****************************************************************************
 */

package com.bytestemplar.tonedef;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bytestemplar.tonedef.utils.UICustom;

public class AboutActivity extends Activity
{
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                this.setContentView(R.layout.about);

                UICustom.getInstance().updateActivity(this);
        }

        public void clickNetwork47(View view)
        {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://Network47.xyz/")));
        }

        public void clickGitHub(View view)
        {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Fortyseven/ToneDef")));
        }

        public void clickClose(View view)
        {
                finish();
        }
}
