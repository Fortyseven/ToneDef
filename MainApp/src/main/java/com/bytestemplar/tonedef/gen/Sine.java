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

package com.bytestemplar.tonedef.gen;

public class Sine
{
    float _increment;
    float _angle;

    public Sine( int freq )
    {
        this._increment = (float) ( 2 * Math.PI ) * freq / 44100;
        this.reset();
    }

    public void reset()
    {
        this._angle = 0;
    }

    public float getNextSample()
    {
        this._angle += this._increment;
        return ( (float) Math.sin( this._angle ) );
    }
}
