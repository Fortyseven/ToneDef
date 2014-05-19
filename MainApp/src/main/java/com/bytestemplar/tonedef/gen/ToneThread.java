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

public class ToneThread extends Thread
{
    private boolean is_running = false;

    void setRunning( boolean state )
    {
        is_running = state;
    }

    @Override
    public void run()
    {
        while ( is_running ) {
            try {
                sleep( 1000 );
            }
            catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }
}
