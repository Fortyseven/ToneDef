package com.bytestemplar.tonedef.international.locations;

// Germany

import android.app.Activity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.international.CountryTones;

public class Germany {
    private static final int DE_FREQ = 425;

    static public CountryTones build(Activity _ownerActivity) {
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
}