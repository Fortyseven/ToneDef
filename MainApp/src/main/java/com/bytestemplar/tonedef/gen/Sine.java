/*******************************************************************************
 * ________                 ____       ____
 * _/_  __/___  ____  ___  / __ \___  / __/
 * __/ / / __ \/ __ \/ _ \/ / / / _ \/ /_
 * _/ / / /_/ / / / /  __/ /_/ /  __/ __/
 * /_/  \____/_/ /_/\___/_____/\___/_/
 *
 * Copyright (c) 2014 Bytes Templar
 * http://BytesTemplar.com/
 *
 * Refer to the license.txt file included for license information.
 * If it is missing, contact fortyseven@gmail.com for details.
 ******************************************************************************/

package com.bytestemplar.tonedef.gen;

public class Sine
{
    float m_increment;
    float m_angle;

    public Sine( int freq )
    {
        this.m_increment = (float) ( 2 * Math.PI ) * freq / 44100;
        this.reset();
    }

    public void reset()
    {
        this.m_angle = 0;
    }

    public float getNextSample()
    {
        this.m_angle += this.m_increment;
        return ( (float) Math.sin( this.m_angle ) );
    }
}
