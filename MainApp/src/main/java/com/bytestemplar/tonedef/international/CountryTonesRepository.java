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

import android.app.Activity;

import com.bytestemplar.tonedef.international.locations.France;
import com.bytestemplar.tonedef.international.locations.Germany;
import com.bytestemplar.tonedef.international.locations.Italy;
import com.bytestemplar.tonedef.international.locations.Japan;
import com.bytestemplar.tonedef.international.locations.UnitedKingdom;
import com.bytestemplar.tonedef.international.locations.UnitedStates;

import java.util.ArrayList;

public class CountryTonesRepository {
    private static CountryTonesRepository instance = null;
    private final Activity _ownerActivity;
    private ArrayList<CountryTones> _country_tones;
    private CountryListAdapter _country_list_adapter;

    /******************************************************************/
    public static CountryTonesRepository getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Must call getInstance at least once with a parent Activity");
        }
        return instance;
    }

    /******************************************************************/
    public static CountryTonesRepository getInstance(Activity owner) {
        if (CountryTonesRepository.instance == null) {
            CountryTonesRepository.instance = new CountryTonesRepository(owner);
        }
        return instance;
    }

    /******************************************************************/
    public CountryListAdapter getCountryListAdapter() {
        return _country_list_adapter;
    }


    /******************************************************************/
    public CountryTones getCountryAtPosition(int position) {
        return _country_tones.get(position);
    }

    /******************************************************************/
    public CountryTonesRepository(Activity owner) {
        _ownerActivity = owner;
        buildCountrySequences();
    }

    /******************************************************************/
    private void buildCountrySequences() {
        _country_tones = new ArrayList<>();

        _country_tones.add(France.build(_ownerActivity));
        _country_tones.add(Germany.build(_ownerActivity));
        _country_tones.add(Italy.build(_ownerActivity));
        _country_tones.add(Japan.build(_ownerActivity));
        _country_tones.add(UnitedKingdom.build(_ownerActivity));
        _country_tones.add(UnitedStates.build(_ownerActivity));

        _country_list_adapter = new CountryListAdapter(_country_tones);
    }
}
