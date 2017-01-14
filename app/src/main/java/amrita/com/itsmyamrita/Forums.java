package amrita.com.itsmyamrita;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yalantis.phoenix.PullToRefreshView;


/**
 * Created by Akhilesh on 9/9/2016.
 */

public class Forums extends AppCompatActivity {
    ListView list;
    TextView head,desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forums);
        head= (TextView) findViewById(R.id.head);
        desc= (TextView) findViewById(R.id.desc);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Barrio-Regular.otf");

        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Quicksand-Regular.otf");
        head.setTypeface(custom_font);
        desc.setTypeface(custom_font1);

        list= (ListView) findViewById(R.id.list_view);

        String[] names={"Technical","Cultural","The Amritadhara Team"};
        int[] imgs={R.drawable.tech,R.drawable.cultural,R.drawable.dhara};
        list.setAdapter(new HomeAdapter(getApplicationContext(),names,imgs));


        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                if(position==0){
                    Intent i = new Intent(Forums.this, Technical.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    Forums.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }

                if(position==1){
                    Intent i = new Intent(Forums.this, Cultural.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    Forums.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }


                if(position==2){
                    Intent i = new Intent(Forums.this, Dhara.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    Forums.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
