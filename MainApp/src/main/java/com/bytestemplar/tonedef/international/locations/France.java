package com.bytestemplar.tonedef.international.locations;

// France
// Specification: https://www.orange.com/sites/orangecom/files/documents/2020-06/STI03-ed4_0505.pdf

import android.app.Activity;

import com.bytestemplar.tonedef.R;
import com.bytestemplar.tonedef.gen.ToneSequence;
import com.bytestemplar.tonedef.international.CountryTones;

public class France {
    static public CountryTones build(Activity _ownerActivity) {
        int FR_FREQ_440 = 440;
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

        //        int FR_FREQ_330 = 330;
        //        int FR_BUSY_ROC_FREQ1 = 494;
        //        int FR_BUSY_ROC_FREQ2 = 554;
        //        int FR_BUSY_ROC_FREQ3 = 659;
        //        int FR_BUSY_ROC_FREQ4 = 738;

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