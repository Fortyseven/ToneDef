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

import android.app.Activity;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.HashMap;

public class TonePanelActivity extends Activity
{
    private OnTouchListener                _button_press;
    private Typeface                       _ttf_dxb;
    private HashMap<Integer, ToneSequence> _momentary_buttons;
    private HashMap<Integer, ToneSequence> _about_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        this._momentary_buttons = new HashMap<Integer, ToneSequence>();
        this._about_buttons = new HashMap<Integer, ToneSequence>();

        this._ttf_dxb = Typeface.createFromAsset(this.getAssets(), "c64.ttf");

        this._button_press = new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int id = v.getId();

                if (TonePanelActivity.this._momentary_buttons.containsKey(id)) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            TonePanelActivity.this._momentary_buttons.get(id).start();
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            TonePanelActivity.this._momentary_buttons.get(id).stop();
                            break;
                    }
                }
                if (TonePanelActivity.this._about_buttons.containsKey(id)) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (!TonePanelActivity.this._about_buttons.get(id)
                                                                      .getDescription()
                                                                      .equals("")) {
                                Toast.makeText(v.getContext(),
                                               TonePanelActivity.this._about_buttons.get(id)
                                                                                    .getDescription(),
                                               Toast.LENGTH_LONG).show();
                            }
                            break;
                    }
                }
                return false;
            } // onClick

        }; // _button_press

    }

    protected void modifyViews(ViewGroup l)
    {
        for (int i = 0; i < l.getChildCount(); i++) {
            View v = l.getChildAt(i);
            if (v instanceof Button) {
                this.updateBitmap((Button) v);
            }
            if (v instanceof LinearLayout) {
                this.modifyViews((ViewGroup) v);
            }
            if (v instanceof ScrollView) {
                this.modifyViews((ViewGroup) v);
            }
            if (v instanceof TextView) {
                this.updateText((TextView) v);
            }
        }
    }

    protected void updateBitmap(Button btn)
    {
        btn.setOnTouchListener(this._button_press);
//        btn.setTypeface( this._ttf_dxb );
//        btn.setSoundEffectsEnabled( false );
    }

    protected void updateText(TextView btn)
    {
        btn.setTypeface(this._ttf_dxb);
    }

    protected void setTitleText(String title)
    {
        // TextView tv = (TextView) findViewById( R.id._title );
        // tv.setText( _title );

        this.setTitle(this.getTitle() + " - " + title);

    }

    protected void setup()
    {
        LinearLayout root = (LinearLayout) this.findViewById(R.id.root);
        if (root == null) {
            try {
                throw new Exception("Require a LinearLayout with id of 'root'");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.modifyViews(root);

    }

    protected void defineButton(int id, int about_id, ToneSequence tonegen)
    {
        this._momentary_buttons.put(id, tonegen);
        this._about_buttons.put(about_id, tonegen);
    }

}
