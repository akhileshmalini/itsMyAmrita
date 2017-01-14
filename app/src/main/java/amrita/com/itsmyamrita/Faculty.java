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



/**
 * Created by Akhilesh on 9/14/2016.
 */

public class Faculty extends AppCompatActivity {
    ListView list;
    TextView head,desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_main);
        head= (TextView) findViewById(R.id.head);
        head.setText("Faculty");
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Barrio-Regular.otf");

        head.setTypeface(custom_font);

        list= (ListView) findViewById(R.id.list_view);

        String[] names={"Computer Science & Engineering ","Electronics & Communication","Physics","Electrical & Electronics","Mechanical","English","Mathematics","Civil","Chemistry","Cultural"};
        int[] imgs={R.drawable.computer,R.drawable.ece,R.drawable.physics,R.drawable.electrical,R.drawable.mechanical,R.drawable.english,R.drawable.math,R.drawable.civil,R.drawable.chemistry,R.drawable.culturalfac};
        list.setAdapter(new HomeAdapter(getApplicationContext(),names,imgs));


        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Bundle bundle = new Bundle();

                if(position==0){
                    Intent i = new Intent(Faculty.this, FacultyList.class);

                    bundle.putString("department", "CSE");
                    i.putExtras(bundle);

                    startActivity(i);
                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==1){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "ECE");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }

                if(position==2){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "PHY");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==3){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "EEE");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==4){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "MECH");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==5){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "ENG");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==6){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "MATH");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==7){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "CIV");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==8){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "CHEM");
                    i.putExtras(bundle);

                    startActivity(i);

                    /** Fading Transition Effect */
                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==9){
                    Intent i = new Intent(Faculty.this, FacultyList.class);
                    bundle.putString("department", "CUL");
                    i.putExtras(bundle);

                    startActivity(i);


                    Faculty.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
