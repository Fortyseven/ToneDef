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

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;

import java.util.ArrayList;

public class CountryTonesRepository {
    private static CountryTonesRepository instance = null;
    private Activity _ownerActivity;
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

        _country_tones.add(buildTonesFrance());
        _country_tones.add(buildTonesGermany());
        _country_tones.add(buildTonesItaly());
        _country_tones.add(buildTonesJapan());
        _country_tones.add(buildTonesUK());
        _country_tones.add(buildTonesUnitedStates());

        _country_list_adapter = new CountryListAdapter(_country_tones);

    }

    /******************************************************************/
    /******************************************************************/
    /******************************************************************/

    // Much thanks to World Tone Database: http://www.3amsystems.com/World_Tone_Database
    private CountryTones buildTonesUnitedStates() {
        CountryTones country = new CountryTones(_ownerActivity.getString(R.string.country_usa));

        ToneSequence ts = new ToneSequence(_ownerActivity);
        ts.addSegment(250, 350, 440)
                .setDescription(_ownerActivity.getString(R.string.desc_dialtone));
        country.addSequence(_ownerActivity.getString(R.string.dialtone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(2000, 440, 480)
                .addSegment(4000, 0)
                //.setDescription( _ownerActivity.getString( R.string.desc_ringback ) );
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.ringback), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, 480, 620)
                .addSegment(500, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.busy), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(250, 480, 620)
                .addSegment(250, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.congestion_fast_busy_disconnect), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(380, 914)
                .addSegment(276, 1429)
                .addSegment(380, 1777)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.sis_ineffective), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(276, 914)
                .addSegment(276, 1371)
                .addSegment(380, 1777)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.sis_intercept), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(380, 985)
                .addSegment(380, 1429)
                .addSegment(380, 1777)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.sis_nocircuit), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(100, 1400, 2060, 2450, 2600)
                .addSegment(100, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.off_hook), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(100, 350, 440)
                .addSegment(100, 0)
                .addSegment(100, 350, 440)
                .addSegment(100, 0)
                .addSegment(100, 350, 440)
                .addSegment(100, 0)
                .addSegment(6000, 350, 440)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.recall_tone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(300, 440)
                .addSegment(10000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.call_waiting), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(100, 350, 440)
                .addSegment(100, 0)
                .addSegment(100, 350, 440)
                .addSegment(100, 0)
                .addSegment(100, 350, 440)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.positive_accepted), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, 200)
                .addSegment(6000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.number_unobtainable), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, 440)
                .addSegment(5000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.record), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, 1400)
                .addSegment(14500, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.warning), ts);

        country.setFlagDrawable(R.drawable.flag_usa);

        return country;
    }

    private CountryTones buildTonesUK() {
        CountryTones country = new CountryTones(_ownerActivity.getString(R.string.country_uk));

        ToneSequence ts = new ToneSequence(_ownerActivity);
        ts.addSegment(250, 350, 440)
                .setDescription(_ownerActivity.getString(R.string.desc_dialtone));
        country.addSequence(_ownerActivity.getString(R.string.dialtone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(400, 400, 450)
                .addSegment(200, 0)
                .addSegment(400, 400, 450)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.ringback), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(375, 400)
                .addSegment(375, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.busy), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(330, 950)
                .addSegment(330, 1400)
                .addSegment(330, 1800)
                .addSegment(1000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.special_information), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(750, 350, 440)
                .addSegment(750, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.special_dial_tone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(20000, 1400)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.confirmation), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(100, 400)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.call_waiting), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(400, 400)
                .addSegment(350, 0)
                .addSegment(225, 400)
                .addSegment(525, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.congestion), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(3000, 400)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.disconnect), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(200, 400)
                .addSegment(400, 0)
                .addSegment(2000, 400)
                .addSegment(400, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.refusal_switching), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(200, 400)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.number_unobtainable), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(125, 400)
                .addSegment(125, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.pay_tone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(200, 1200)
                .addSegment(200, 0)
                .addSegment(200, 800)
                .addSegment(2000, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.payphone_recognition), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(20000, 1400)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.positive_accepted), ts);

        country.setFlagDrawable(R.drawable.flag_uk);

        return country;
    }

    /******************************************************************/
    private final int DE_FREQ = 425;

    private CountryTones buildTonesGermany() {
        CountryTones country = new CountryTones(_ownerActivity.getString(R.string.country_germany));

        ToneSequence ts = new ToneSequence(_ownerActivity);
        ts.addSegment(250, DE_FREQ)
                .setDescription(_ownerActivity.getString(R.string.desc_dialtone));

        country.addSequence(_ownerActivity.getString(R.string.dialtone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(1000, DE_FREQ)
                .addSegment(4000, 0)
                .setDescription(_ownerActivity.getString(R.string.desc_ringback));
        country.addSequence(_ownerActivity.getString(R.string.ringback), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, DE_FREQ)
                .addSegment(500, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.busy), ts);

        country.setFlagDrawable(R.drawable.flag_germany);

        return country;
    }

    /******************************************************************/
    private CountryTones buildTonesJapan() {
        int JP_DIALTONE_FREQ = 400;
        int JP_RINGBACK_FREQ1 = 384;
        int JP_RINGBACK_FREQ2 = 416;
        int JP_BUSY_FREQ = 400;

        CountryTones country = new CountryTones(_ownerActivity.getString(R.string.country_japan));

        ToneSequence ts = new ToneSequence(_ownerActivity);
        ts.addSegment(250, JP_DIALTONE_FREQ)
                .setDescription(_ownerActivity.getString(R.string.desc_dialtone));

        country.addSequence(_ownerActivity.getString(R.string.dialtone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(1000, JP_RINGBACK_FREQ1, JP_RINGBACK_FREQ2)
                .addSegment(2000, 0)
                .setDescription(_ownerActivity.getString(R.string.desc_ringback));

        country.addSequence(_ownerActivity.getString(R.string.ringback), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, JP_BUSY_FREQ)
                .addSegment(500, 0)
                .setDescription("");

        country.addSequence(_ownerActivity.getString(R.string.busy), ts);

        country.setFlagDrawable(R.drawable.flag_japan);
        return country;
    }

    /****************************************************************/
    private CountryTones buildTonesItaly() {
        int ITALY_FREQ = 425;

        CountryTones country = new CountryTones(_ownerActivity.getString(R.string.country_italy));

        ToneSequence ts = new ToneSequence(_ownerActivity);

        ts.addSegment(1000, ITALY_FREQ)
                .addSegment(4000, 0)
                .setDescription(_ownerActivity.getString(R.string.desc_ringback));

        country.addSequence(_ownerActivity.getString(R.string.ringback), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(200, ITALY_FREQ)
                .addSegment(200, 0)
                .addSegment(600, ITALY_FREQ)
                .addSegment(1000, 0)
                .setDescription(_ownerActivity.getString(R.string.desc_dialtone));
        country.addSequence(_ownerActivity.getString(R.string.dialtone), ts);

        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, ITALY_FREQ)
                .addSegment(500, 0)
                .setDescription("");
        country.addSequence(_ownerActivity.getString(R.string.busy), ts);

        country.setFlagDrawable(R.drawable.flag_italy);

        return country;
    }


    /******************************************************************/
    // France
    // Specification: https://www.orange.com/sites/orangecom/files/documents/2020-06/STI03-ed4_0505.pdf
    // Based on PR provided by @ggramaize on GH
    private CountryTones buildTonesFrance() {
        int FR_FREQ_400 = 400;
        int FR_FREQ_440 = 440;
        int FR_FREQ_330 = 330;
        int FR_BUSY_ROC_FREQ1 = 494;
        int FR_BUSY_ROC_FREQ2 = 554;
        int FR_BUSY_ROC_FREQ3 = 659;
        int FR_BUSY_ROC_FREQ4 = 738;
        int FR_SIT_FREQ1 = 950;
        int FR_SIT_FREQ2 = 1400;
        int FR_SIT_FREQ3 = 1800;

        CountryTones country = new CountryTones(_ownerActivity.getString(R.string.country_france));

        /* DIALTONE ----------- */
        ToneSequence ts = new ToneSequence(_ownerActivity);
        ts.addSegment(250, FR_FREQ_440)
                .setDescription(_ownerActivity.getString(R.string.desc_dialtone));

        country.addSequence(_ownerActivity.getString(R.string.dialtone), ts); // OK

        /* RINGBACK ----------- */
        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(1500, FR_FREQ_440)
                .addSegment(3500, 0)
                .setDescription(_ownerActivity.getString(R.string.desc_ringback));

        country.addSequence(_ownerActivity.getString(R.string.ringback), ts); // OK

        /* BUSY ----------- */
        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(500, FR_FREQ_440)
                .addSegment(500, 0)
                .setDescription("");

        country.addSequence(_ownerActivity.getString(R.string.busy), ts); // OK

        /* SIT ----------- */
        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(300, FR_SIT_FREQ1)
                .addSegment(30, 0)
                .addSegment(300, FR_SIT_FREQ2)
                .addSegment(30, 0)
                .addSegment(300, FR_SIT_FREQ3)
                .addSegment(1030, 0) // The reasoning: (3 x (0.3 on, 0.03 off) / 1.0 off
                .setDescription("");

        country.addSequence(_ownerActivity.getString(R.string.special_information), ts);

        /* CALL WAITING ----------- */
        ts = new ToneSequence(_ownerActivity);
        ts.addSegment(300, FR_FREQ_440)
                .addSegment(10000, 0)
                .setDescription("");

        country.addSequence("Call Waiting", ts);

        /* ----------- */

        // TODO: We'll add the rest of these when I can confirm.

        /* ----------- */
        //        ts = new ToneSequence( _ownerActivity );
        //        ts.addSegment( 250, FR_FREQ_330, FR_FREQ_440 )
        //          .setDescription( _ownerActivity.getString( R.string.desc_dialtone_sac ) );
        //
        //        country.addSequence( _ownerActivity.getString( R.string.dialtone_sac ), ts );

        /* ----------- */
        //        ts = new ToneSequence( _ownerActivity );
        //        ts.addSegment(  50, FR_FREQ_330, FR_FREQ_440 )
        //          .addSegment( 150, FR_FREQ_440 )
        //          .addSegment(  50, FR_FREQ_330, FR_FREQ_440 )
        //          .addSegment( 300, FR_FREQ_440 )
        //          .setDescription( _ownerActivity.getString( R.string.desc_dialtone_mwi ) );
        //
        //        country.addSequence( _ownerActivity.getString( R.string.dialtone_mwi ), ts );

        /* ----------- */
        //        ts = new ToneSequence( _ownerActivity );
        //        ts.addSegment( 500, FR_FREQ_440, FR_BUSY_ROC_FREQ2 )
        //          .addSegment( 500, 0 )
        //          .addSegment( 500, FR_FREQ_440, FR_BUSY_ROC_FREQ4 )
        //          .addSegment( 500, 0 )
        //          .addSegment( 500, FR_FREQ_440, FR_BUSY_ROC_FREQ3 )
        //          .addSegment( 500, 0 )
        //          .addSegment( 500, FR_FREQ_440, FR_BUSY_ROC_FREQ2 )
        //          .addSegment( 500, 0 )
        //          .addSegment( 500, FR_FREQ_440, FR_BUSY_ROC_FREQ1 )
        //          .addSegment( 500, 0 )
        //          .addSegment( 500, FR_FREQ_440, FR_BUSY_ROC_FREQ2 )
        //          .addSegment( 60000, 0 )
        //          .setDescription( _ownerActivity.getString(R.string.desc_busy_roc) );
        //
        //        country.addSequence( _ownerActivity.getString( R.string.busy_roc ), ts );


        country.setFlagDrawable(R.drawable.flag_french);
        return country;
    }
}
