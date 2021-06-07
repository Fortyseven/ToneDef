package com.bytestemplar.tonedef.international.locations;

// United Kingdom

import android.app.Activity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.international.CountryTones;

public class UnitedKingdom {
    static public CountryTones build(Activity _ownerActivity) {
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
}