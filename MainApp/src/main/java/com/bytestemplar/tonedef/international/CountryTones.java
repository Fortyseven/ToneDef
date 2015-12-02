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

import com.bytestemplar.tonedef.gen.ToneSequence;
import java.util.HashMap;

class CountryTones
{
    private String                        _name;
    private HashMap<String, ToneSequence> _sequences;

    public CountryTones( String country_name )
    {
        _name = country_name;
        _sequences = new HashMap<>();
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
}