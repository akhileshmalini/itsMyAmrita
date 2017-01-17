package amrita.com.itsmyamrita;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Akhilesh on 1/14/2017.
 */

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutbangalore);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Barrio-Regular.otf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Quicksand-Regular.otf");
        TextView a = (TextView) findViewById(R.id.textView1);
        TextView b = (TextView) findViewById(R.id.textView16);

        a.setTypeface(custom_font);
        b.setTypeface(custom_font1);

        b.setText("The Amrita School of Engineering, " +
                "Bengaluru offers B. Tech. programs in five disciplines and M. Tech. programs in six disciplines. " +
                "The school seeks to prepare graduates with a solution-mindset and with a high degree of ethical standards. " +
                "Recruiters from the best companies and institutes in India and abroad seek out these students.\n\n" +
                "Curriculum is framed with extensive industry input. All programs are credit-based. Departments are equipped" +
                " with modern laboratories, design tools and software packages. Emphasis is laid on continuous evaluation of students. " +
                "Faculty serve as class advisors and mentors to students.\n\nThe school has a faculty strength of nearly one hundred " +
                "and fifty. Several faculty have come to Amrita with rich experience from leading organizations and universities. " +
                "The school traces its roots to the Amrita Institute of Technology & Science that was started in 2002 with a vision " +
                "to produce quality engineers with an attitude of service, for the benefit of the society and nation.\n\n");
    }
}
