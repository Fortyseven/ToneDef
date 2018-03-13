
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

package com.bytestemplar.tonedef.touchpad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bytestemplar.tonedef.ConfigActivity;
import com.bytestemplar.tonedef.ContactList;
import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.tones.ToneBank;
import com.bytestemplar.tonedef.utils.UICustom;

import static android.text.InputType.TYPE_CLASS_TEXT;

public abstract class TouchPadActivity extends Activity implements OnTouchListener
{
    protected static final boolean DISABLE_DIALINGSTRING = true;
    protected static final boolean ENABLE_DIALINGSTRING  = false;
    protected static final boolean DISABLE_MENU          = true;
    protected static final boolean ENABLE_MENU           = false;

    protected SharedPreferences _preferences = null;

    protected EditText _et_dialing_string = null;

    private ToneButtonList _buttons = null;

    private int      _touchpad_layout = 0;
    private ToneBank _tonebank        = null;

    private ToneSequence _generated_dial_sequence = null;

    private String _pref_mark  = null;
    private String _pref_space = null;
    private String _pref_delay = null;


    private int _delay_mark  = 750;
    private int _delay_space = 250;
    private int _del_delay   = 1000;

    private boolean _disable_menu = false;

    float _invert[] = {
            -1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, -1.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, -1.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    };

    private ColorFilter _invert_paint;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.touchpad_container );
        setVolumeControlStream( AudioManager.STREAM_MUSIC );
        _preferences = PreferenceManager.getDefaultSharedPreferences( this );

        _et_dialing_string = (EditText) findViewById( R.id.etDialingString );
        _et_dialing_string.setInputType(TYPE_CLASS_TEXT);
        _buttons = new ToneButtonList( this );
    }

    protected void setup( boolean disable_dialstring, boolean disable_menu ) throws Exception
    {
        _disable_menu = disable_menu;

        // only load preferences if we've been supplied their id
        if ( ( _pref_mark != null ) && ( _pref_delay != null ) && ( _pref_space != null ) ) {
            _delay_mark = Integer.parseInt( _preferences.getString( _pref_mark, "250" ) );
            _delay_space = Integer.parseInt( _preferences.getString( _pref_space, "100" ) );
            _del_delay = Integer.parseInt( _preferences.getString( _pref_delay, "1000" ) );
        }

        // ensure we have a _tonebank
        if ( _tonebank == null ) {
            throw new Exception( "No tone bank defined (use setToneBank)" );
        }

        // ensure we have a layout to inflate
        if ( _touchpad_layout == 0 ) {
            throw new Exception( "No container layout ID specified (use setTouchPadLayoutId)" );
        }

        // inflate layout
        LinearLayout content_container = (LinearLayout) findViewById( R.id.touchpad_content );
        LayoutInflater
                inflater =
                (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        inflater.inflate( _touchpad_layout, content_container, true );

        // let descendant define _buttons
        defineToneButtons( _buttons );

        if ( _buttons.buttons.size() == 0 ) {
            throw new Exception( "At least one button must be defined via defineToneButtons" );
        }

        assertInvertState( true );


        if ( disable_dialstring ) {
            LinearLayout ll = (LinearLayout) findViewById( R.id.touchpad_dialingstring );
            ll.setVisibility( View.GONE );
        }

        UICustom.getInstance().updateActivity( this );
    }

    public void setPreferenceIdentifiers( String pref_mark, String pref_space, String pref_delay )
    {
        _pref_mark = pref_mark;
        _pref_space = pref_space;
        _pref_delay = pref_delay;
    }

    public void setToneBank( ToneBank tone_bank )
    {
        _tonebank = tone_bank;
    }

    protected void setTouchPadLayoutId( int touchpad_layout )
    {
        _touchpad_layout = touchpad_layout;
    }

    public void playDialingString( String digits )
    {
        if ( digits.length() > 0 ) {
            // Do not start playing again if sequence is in-progress
            if ( _generated_dial_sequence != null ) {
                if ( _generated_dial_sequence.isPlaying() ) {
                    return;
                }
            }

            _generated_dial_sequence =
                    _tonebank.buildToneSequence( _delay_mark, _delay_space, _del_delay, digits );
            _generated_dial_sequence.setIterations( 1 );
            _generated_dial_sequence.start();
        }
        else {
            Toast.makeText( this, "Dialing string is empty!", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public boolean onTouch( View v, MotionEvent event )
    {
        for ( ButtonDefinition button : _buttons.buttons ) {

            if ( button.getButtonView().getId() == v.getId() ) {

                ToneBank.Entry entry = _tonebank.getEntry( button.getId() );

                if ( entry != null ) {

                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            entry.start();
                            button.getButtonView()
                                  .setBackgroundResource( R.drawable.touchpadbutton_selected );
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            entry.stop();
                            button.getButtonView()
                                  .setBackgroundResource( R.drawable.touchpadbutton );
                            break;
                        default:
                            break;
                    }
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        if ( !_disable_menu ) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate( R.menu.touchpad_menu, menu );
        }

        return true;
    }

    @Override
    public boolean onMenuItemSelected( int featureId, MenuItem item )
    {
        if ( !_disable_menu ) {
            if ( item.getItemId() == R.id.menu_contactpick ) {
                ContactList.fetch( this );
                return true;
            }

            if ( item.getItemId() == R.id.menu_settings ) {
                startActivity( new Intent( getApplicationContext(), ConfigActivity.class ) );
            }
        }

        return super.onMenuItemSelected( featureId, item );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        if ( requestCode == ContactList.RESULT_CODE ) {
            String result = ContactList.parseResponse( this, requestCode, resultCode, data );
            if ( result != null ) {
                _et_dialing_string.setText( result );
            }
        }

        super.onActivityResult( requestCode, resultCode, data );
    }

    public void clickDial( View v )
    {
        Editable text = _et_dialing_string.getText();

        if ( text != null ) {
            playDialingString( text.toString() );
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        assertInvertState( false );
    }

    private void assertInvertState( boolean hook_buttons )
    {
        boolean mInvertKeyColors = false;
        if ( _preferences.getBoolean( "invert_text_color", false ) ) {
            mInvertKeyColors = true;
            ColorMatrix cm = new ColorMatrix( _invert );
            _invert_paint = new ColorMatrixColorFilter( cm );
        }

        // hook those _buttons
        for ( int i = 0; i < _buttons.buttons.size(); i++ ) {
            ButtonDefinition button = _buttons.buttons.get( i );

            if ( hook_buttons ) {
                button.getButtonView().setOnTouchListener( this );
                button.getButtonView().setSoundEffectsEnabled( false );
            }

            if ( button.getButtonView() instanceof ImageButton ) {

                Drawable ib = ( (ImageButton) ( button.getButtonView() ) ).getDrawable();

                if ( ib != null ) {
                    if ( mInvertKeyColors ) {
                        ib.setColorFilter( _invert_paint );
                    }
                    else {
                        ib.setColorFilter( null );
                    }
                }
            }
        }
    }

    protected abstract void defineToneButtons( ToneButtonList buttons );
}
