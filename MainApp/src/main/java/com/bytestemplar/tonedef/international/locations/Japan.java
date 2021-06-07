package com.bytestemplar.tonedef.international.locations;

// Japan

import android.app.Activity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.international.CountryTones;

public class Japan {
    private static final int JP_DIALTONE_FREQ = 400;
    private static final int JP_RINGBACK_FREQ1 = 384;
    private static final int JP_RINGBACK_FREQ2 = 416;
    private static final int JP_BUSY_FREQ = 400;

    static public CountryTones build(Activity _ownerActivity) {


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
}