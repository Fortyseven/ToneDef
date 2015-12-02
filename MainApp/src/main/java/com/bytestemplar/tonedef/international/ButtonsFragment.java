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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ButtonsFragment extends Fragment
{
    public static final String ARG_POSITION = "position";
    private InternationalActivity _parent;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        _parent = (InternationalActivity) getActivity();
        return inflater.inflate( R.layout.frag_buttons, container, false );
    }

    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState )
    {
        super.onViewCreated( view, savedInstanceState );

        Bundle args = getArguments();

        if ( args != null ) {

            int position = getArguments().getInt( ARG_POSITION );
            updateButtons( position );
        }
    }

    public void updateButtons( int position )
    {
        LinearLayout ll_btn_container = (LinearLayout) getView().findViewById( R.id.buttons_container );

        if ( ll_btn_container != null ) {


            ll_btn_container.removeAllViewsInLayout();

            CountryTones ct = _parent.getCountryAtPosition( position );

            // Update label
            TextView tv_name = (TextView) getView().findViewById( R.id.tv_countryname );
            tv_name.setText( ct.getName() );
            tv_name.setTypeface( _parent.getCustomTypeface() );

            // Generate buttons

            HashMap<String, ToneSequence> sequences = ct.getSequences();

            Iterator i = sequences.entrySet().iterator();

            while ( i.hasNext() ) {
                Map.Entry pair = (Map.Entry) i.next();

                String sequence_name = (String) pair.getKey();
                final ToneSequence sequence = (ToneSequence) pair.getValue();

                Button btn = new Button( ll_btn_container.getContext() );
                btn.setText( sequence_name );
                btn.setTypeface( _parent.getCustomTypeface() );
                btn.setOnTouchListener( new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch( View v, MotionEvent event )
                    {
                        switch ( event.getAction() ) {
                            case MotionEvent.ACTION_DOWN:
                                sequence.start();
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:
                                sequence.stop();
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
}
