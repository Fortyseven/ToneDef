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

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.HashMap;

public class CountryTones
{
    private String                        _name;
    private HashMap<String, ToneSequence> _sequences;
    private int                           _flag_drawable;

    public CountryTones( String country_name )
    {
        _name = country_name;
        _sequences = new HashMap<>();
        _flag_drawable = R.drawable.flag_default;
    }

    public String getName()
    {
        return _name;
    }


    public void addSequence( String name, ToneSequence sequence )
    {
        _sequences.put( name, sequence );
    }

    public HashMap<String, ToneSequence> getSequences()
    {
        return _sequences;
    }

    public void setFlagDrawable( int flag_resource )
    {
        _flag_drawable = flag_resource;
    }

    public int getFlagDrawable()
    {
        return _flag_drawable;
    }
}