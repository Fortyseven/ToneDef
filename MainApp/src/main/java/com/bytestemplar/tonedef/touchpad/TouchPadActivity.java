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

import com.bytestemplar.tonedef.ContactList;
import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.tones.ToneBank;
import com.bytestemplar.tonedef.utils.UICustom;

public abstract class TouchPadActivity extends Activity implements OnTouchListener
{
    protected static final boolean DISABLE_DIALINGSTRING = true;
    protected static final boolean ENABLE_DIALINGSTRING  = false;
    protected static final boolean DISABLE_MENU          = true;
    protected static final boolean ENABLE_MENU           = false;

    protected SharedPreferences mPrefs = null;

    private EditText mEtDialingString = null;

    private ToneButtonList mButtons = null;

    private int      mTouchpadLayout = 0;
    private ToneBank mToneBank       = null;

    private String mPrefMark  = null;
    private String mPrefSpace = null;
    private String mPrefDelay = null;


    private int mDelayMark  = 750;
    private int mDelaySpace = 250;
    private int mDelDelay   = 1000;

    private boolean mDisableMenu = false;

    float invert[] = {
            -1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
            0.0f, -1.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, -1.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    };

    private ColorFilter invertPaint;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.touchpad_container );
        setVolumeControlStream( AudioManager.STREAM_MUSIC );
        mPrefs = PreferenceManager.getDefaultSharedPreferences( this );

        mEtDialingString = (EditText) findViewById( R.id.etDialingString );
        mButtons = new ToneButtonList( this );
    }

    protected void setup( boolean disable_dialstring, boolean disable_menu ) throws Exception
    {
        mDisableMenu = disable_menu;

        // only load preferences if we've been supplied their id
        if ( ( mPrefMark != null ) && ( mPrefDelay != null ) && ( mPrefSpace != null ) ) {
            mDelayMark = Integer.valueOf( mPrefs.getString( mPrefMark, "250" ) );
            mDelaySpace = Integer.valueOf( mPrefs.getString( mPrefSpace, "100" ) );
            mDelDelay = Integer.valueOf( mPrefs.getString( mPrefDelay, "1000" ) );
        }

        // ensure we have a mToneBank
        if ( mToneBank == null ) {
            throw new Exception( "No tone bank defined (use setToneBank)" );
        }

        // ensure we have a layout to inflate
        if ( mTouchpadLayout == 0 ) {
            throw new Exception( "No container layout ID specified (use setTouchPadLayoutId)" );
        }

        // inflate layout
        LinearLayout content_container = (LinearLayout) findViewById( R.id.touchpad_content );
        LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        inflater.inflate( mTouchpadLayout, content_container, true );

        // let descendant define mButtons
        defineToneButtons( mButtons );

        if ( mButtons.buttons.size() == 0 ) {
            throw new Exception( "At least one button must be defined via defineToneButtons" );
        }

        boolean mInvertKeyColors = false;
        if ( mPrefs.getBoolean( "invert_text_color", false ) ) {
            mInvertKeyColors = true;
            ColorMatrix cm = new ColorMatrix( invert );
            invertPaint = new ColorMatrixColorFilter( cm );
        }

        // hook those mButtons
        for ( int i = 0; i < mButtons.buttons.size(); i++ ) {
            ButtonDefinition button = mButtons.buttons.get( i );
            button.btnView.setOnTouchListener( this );
            button.btnView.setSoundEffectsEnabled( false );

            if ( button.btnView instanceof ImageButton ) {

                Drawable ib = ( (ImageButton) ( button.btnView ) ).getDrawable();

                if ( ib != null ) {
                    if ( mInvertKeyColors ) {
                        ib.setColorFilter( invertPaint );
                    }
                    else {
                        ib.setColorFilter( null );
                    }
                }
            }
        }

        if ( disable_dialstring ) {
            LinearLayout ll = (LinearLayout) findViewById( R.id.touchpad_dialingstring );
            ll.setVisibility( View.GONE );
        }

        UICustom.getInstance().updateActivity( this );
    }

    public void setPreferenceIdentifiers( String pref_mark, String pref_space, String pref_delay )
    {
        mPrefMark = pref_mark;
        mPrefSpace = pref_space;
        mPrefDelay = pref_delay;
    }

    public void setToneBank( ToneBank tone_bank )
    {
        mToneBank = tone_bank;
    }

    protected void setTouchPadLayoutId( int touchpad_layout )
    {
        mTouchpadLayout = touchpad_layout;
    }

    public void playDialingString( String digits )
    {
        if ( digits.length() > 0 ) {
            ToneSequence generated_sequence;

            generated_sequence = mToneBank.buildToneSequence( mDelayMark, mDelaySpace, mDelDelay, digits );
            generated_sequence.setIterations( 1 );
            generated_sequence.start();
        }
        else {
            Toast.makeText( this, "Dialing string is empty!", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public boolean onTouch( View v, MotionEvent event )
    {
        for ( ButtonDefinition button : mButtons.buttons ) {
            if ( button.btnView.getId() == v.getId() ) {
                ToneBank.Entry entry = mToneBank.getEntry( button.id );
                if ( entry != null ) {
                    switch ( event.getAction() ) {
                        case MotionEvent.ACTION_DOWN:
                            entry.start();
                            button.btnView.setBackgroundResource( R.drawable.touchpadbutton_selected );
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            entry.stop();
                            button.btnView.setBackgroundResource( R.drawable.touchpadbutton );
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
        if ( !mDisableMenu ) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate( R.menu.touchpad_menu, menu );
        }
        return true;
    }

    @Override
    public boolean onMenuItemSelected( int featureId, MenuItem item )
    {
        if ( !mDisableMenu ) {
            if ( item.getItemId() == R.id.menu_contactpick ) {
                ContactList.fetch( this );
                return true;
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
                mEtDialingString.setText( result );
            }
        }

        super.onActivityResult( requestCode, resultCode, data );
    }

    public void clickDial( View v )
    {
        Editable text = mEtDialingString.getText();

        if ( text != null ) {
            playDialingString( text.toString() );
        }
    }

    protected abstract void defineToneButtons( ToneButtonList buttons );
}
