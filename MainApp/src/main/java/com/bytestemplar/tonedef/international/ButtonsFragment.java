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

package com.bytestemplar.tonedef.international;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.utils.UICustom;

import java.util.HashMap;
import java.util.Map;

public class ButtonsFragment extends Fragment
{
    public static final String ARG_POSITION = "position";

    private int _current_position;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.frag_buttons, container, false );
    }

    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState )
    {
        super.onViewCreated( view, savedInstanceState );

        TextView tv_name = (TextView) getView().findViewById( R.id.tv_countryname );
        tv_name.setTypeface( UICustom.getInstance().getTypeface() );

        int position = -1;

        if ( savedInstanceState != null ) {
            position = savedInstanceState.getInt( ARG_POSITION );

        }
        else {
            Bundle args = getArguments();
            if ( args != null ) {
                position = getArguments().getInt( ARG_POSITION );
            }
        }

        if ( position >= 0 ) {
            updateButtons( position );
        }


    }

    public void updateButtons( int position )
    {
        LinearLayout ll_btn_container = (LinearLayout) getView().findViewById( R.id.buttons_container );

        if ( ll_btn_container != null ) {

            _current_position = position;
            CountryTones current_tones = CountryTonesRepository.getInstance().getCountryAtPosition( position );

            ll_btn_container.removeAllViewsInLayout();

            // Update label
            TextView tv_name = (TextView) getView().findViewById( R.id.tv_countryname );
            tv_name.setText( current_tones.getName() );
            tv_name.setTypeface( UICustom.getInstance().getTypeface() );
            if ( current_tones.getFlagDrawable() > 0 ) {
                tv_name.setCompoundDrawablesWithIntrinsicBounds( current_tones.getFlagDrawable(), 0, 0, 0 );
            }

            // Generate buttons
            HashMap<String, ToneSequence> sequences = current_tones.getSequences();

            for ( Object o : sequences.entrySet() ) {
                Map.Entry pair = (Map.Entry) o;

                String sequence_name = (String) pair.getKey();
                final ToneSequence sequence = (ToneSequence) pair.getValue();

                Button btn = new Button( ll_btn_container.getContext() );
                btn.setText( sequence_name );
                btn.setTypeface( UICustom.getInstance().getTypeface() );
                btn.setBackgroundResource( R.drawable.touchpadbutton );
                btn.setTextColor( Color.WHITE );
                btn.setOnTouchListener( new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch( View v, MotionEvent event )
                    {
                        switch ( event.getAction() ) {
                            case MotionEvent.ACTION_DOWN:
                                sequence.start();
                                v.setBackgroundResource( R.drawable.touchpadbutton_selected );
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:
                                sequence.stop();
                                v.setBackgroundResource( R.drawable.touchpadbutton );
                                break;
                        }
                        return false;
                    }
                } );
                btn.setLayoutParams( new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
                ll_btn_container.addView( btn );
            }
        }
    }

    @Override
    public void onSaveInstanceState( Bundle out_state )
    {
        super.onSaveInstanceState( out_state );

        out_state.putInt( ARG_POSITION, _current_position );
    }
}
