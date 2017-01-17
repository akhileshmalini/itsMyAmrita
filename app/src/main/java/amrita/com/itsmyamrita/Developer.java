package amrita.com.itsmyamrita;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Akhilesh on 1/16/2017.
 */

public class Developer extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developers);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Barrio-Regular.otf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Quicksand-Regular.otf");
        TextView b = (TextView) findViewById(R.id.txtpost);
        TextView a = (TextView) findViewById(R.id.txtname);
        TextView c = (TextView) findViewById(R.id.txt);


        c.setText("ItsMyAmrita is a platform that provides information on our esteemed institution, our highly qualified faculty and their research interests, and the student activities ensemble Amritadhara, and issues regular updates on events of interest in and around the campus.\n" +
                "The application also has a soft copy of the annual calendar for convenience, and a link to AHAN, the school's official blog, which possesses further information on events conducted.\n" +
                "\nDeveloped by FACE, the application is maintained in association with Lekhani, The Press Club.");
        a.setTypeface(custom_font);
        b.setTypeface(custom_font1);
        c.setTypeface(custom_font1);

    }
}
