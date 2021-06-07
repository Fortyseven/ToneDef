package com.bytestemplar.tonedef.international.locations;

// United States

import android.app.Activity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.international.CountryTones;

public class UnitedStates {
    static public CountryTones build(Activity _ownerActivity) {
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
}