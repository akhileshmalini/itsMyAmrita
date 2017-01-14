package amrita.com.itsmyamrita;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by Akhilesh on 9/17/2016.
 */

public class Technical extends AppCompatActivity {

    private RecyclerView list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technical);


        list= (RecyclerView) findViewById(R.id.list);





        String[] names={"FACE ",
                "ECIF",
                "Ingenium",
                "Vidyuth",
                "Aavishkara",
                "CSI",
                "SAE",
                "ACM",
                "IEEE",
                "IEEE PELS",
                "IEEE PES",
                "IEEE WIE",
                "IEEE COMSOC",
                "ACROM",
                "ANC",
                "ACE",
                "Sankhya",
                "Vyom",
                "JIDO"
                        };
        String[] desc={"Forum for Aspiring Computer Engineers ",
                "Electronics, Communication and Instrumentation Forum",
                "The Mechanical Dept. Forum",
                "The Electrical Dept. Forum",
                "The Science Forum",
                "Computer Society of India",
                "The Society of Automotive Engineers",
                "The Association of Computing Machinery",
                "Student Chapter",
                "The Power Electronics Society",
                "The Power and Energy Society",
                "The Women in Engineering Society",
                "The Comminication Society",
                "Amrita Centre for Robotics and Mechatronics",
                "Amrita Networking Club",
                "Amrita Center for Entrepreneurship",
                "The Mathematics Club",
                "The Astrophysics Club",
                "The Industrial Automation Club"
        };
        int[] imgs= {R.drawable.face,
                R.drawable.ecif,
                R.drawable.ingenium,
                R.drawable.vidyuths,
                R.drawable.aavishkara,
                R.drawable.csi,
                R.drawable.sae,
                R.drawable.acm,
                R.drawable.ieee,
                R.drawable.pels,
                R.drawable.pes,
                R.drawable.wie,
                R.drawable.comsoc,
                R.drawable.acroms,
                R.drawable.anc,
                R.drawable.ace,
                R.drawable.sankhyas,
                R.drawable.vyoms,
                R.drawable.jido


        };
        final ClubAdapter adapter =new ClubAdapter(getApplicationContext(),names,imgs,desc);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(mLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(adapter);


        list.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), list ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i;
                        Bundle bundle = new Bundle();


                        if (position == 0) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "FACE");
                            bundle.putString("clubs", "Forum for Aspiring Computer Engineers");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 1) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "ECIF");
                            bundle.putString("clubs", "Electronics, Communication and Instrumentation Forum");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }

                        else if (position == 2) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "INGENIUM");
                            bundle.putString("clubs", "The Mechanical Dept. Forum");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 3) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "VIDYUTH");
                            bundle.putString("clubs", "The Electrical Dept. Forum");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 4) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "AAVISHKARA");
                            bundle.putString("clubs", "The Science Forum");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 5) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "CSI");
                            bundle.putString("clubs", "Computer Society of India");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 6) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "SAE");
                            bundle.putString("clubs", "Society of Automotive Engineers");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 7) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "ACM");
                            bundle.putString("clubs", "The Association of Computing Machinery");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 8) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "IEEE");
                            bundle.putString("clubs", "Student Chapter");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 9) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "PELS");
                            bundle.putString("clubs", "The Power Electronics Society");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 10) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "PES");
                            bundle.putString("clubs", "The Power and Energy Society");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 11) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "WIE");
                            bundle.putString("clubs", "The Women in Engineering Society");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 12) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "COMSOC");
                            bundle.putString("clubs", "The Communications Society");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 13) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "ACROM");
                            bundle.putString("clubs", "Amrita Centre for Robotics and Mechatronics");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 14) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "ANC");
                            bundle.putString("clubs", "Amrita Networking Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 15) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "ACE");
                            bundle.putString("clubs", "Amrita Center for Entrepreneurship");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 16) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "SANKHYA");
                            bundle.putString("clubs", "The Mathematics Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 17) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "VYOM");
                            bundle.putString("clubs", "The Astrophysics Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }   else if (position == 18) {
                            i= new Intent(Technical.this, ClubMain.class);
                            bundle.putString("department", "JIDO");
                            bundle.putString("clubs", "The Industrial Automation Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Technical.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }




                }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
