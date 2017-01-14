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


/**
 * Created by Akhilesh on 9/17/2016.
 */

public class Cultural extends AppCompatActivity {

    private RecyclerView list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technical);


        list= (RecyclerView) findViewById(R.id.list);





        String[] names={"Maardhani",
                " Abhinaya",
                "Kala",
                "Lekhani",
                "Chetana",
                "Narthana",
                "Raaga",
                "EPIC",
                "Smriti",
                "SPICMACAY",
                "Prakriti",
                "Prerana",
                "Vedanta",
                "Quiz",
                "The Squad",
                "Humour"

                        };
        String[] desc={"The Literary Club",
                "The Drama Club",
                "The Fine Arts Club",
                "The Press Club",
                "The Sports Club",
                "The Dance Club",
                "The Music Club",
                "The Movie Club",
                "The Photography Club",
                "The Indian Arts Club ",
                "The Eco Club",
                "The Social Outreach Club",
                "The Cultural Club",
                "The Quiz Club",
                "The Adventure Enthusiasts Club",
                "The Humour Club"
        };
        int[] imgs= {R.drawable.maardhani,
                R.drawable.abhinaya,
                R.drawable.kala,
                R.drawable.lekhani,
                R.drawable.chetana,
                R.drawable.narthana,
                R.drawable.raagas,
                R.drawable.epic,
                R.drawable.smritis,
                R.drawable.spicmacay,
                R.drawable.prakriti,
                R.drawable.prerana,
                R.drawable.vedanta,
                R.drawable.quiz,
                R.drawable.squad,
                R.drawable.humour

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
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "MAARDHANI");
                            bundle.putString("clubs", "The Literary Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 1) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "ABHINAYA");
                            bundle.putString("clubs", "The Drama Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }

                        else if (position == 2) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "KALA");
                            bundle.putString("clubs", "The Fine Arts Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 3) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "LEKHANI");
                            bundle.putString("clubs", "The Press Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 4) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "CHETANA");
                            bundle.putString("clubs", "The Sports Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 5) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "NARTHANA");
                            bundle.putString("clubs", "The Dance Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 6) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "RAAGA");
                            bundle.putString("clubs", "The Music Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 7) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "EPIC");
                            bundle.putString("clubs", "The Movie Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 8) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "SMRITI");
                            bundle.putString("clubs", "The Photography Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 9) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "SPICMACAY");
                            bundle.putString("clubs", "The Indian Arts Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 10) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "PRAKRITI");
                            bundle.putString("clubs", "The Eco Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 11) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "PRERANA");
                            bundle.putString("clubs", "The Social Outreach Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 12) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "VEDANTA");
                            bundle.putString("clubs", "The Cultural Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 13) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "QUIZ");
                            bundle.putString("clubs", "The Quiz Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 14) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "SQUAD");
                            bundle.putString("clubs", "The Adventure Enthusiasts Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else if (position == 15) {
                            i= new Intent(Cultural.this, ClubMain.class);
                            bundle.putString("department", "HUMOUR");
                            bundle.putString("clubs", "The Humour Club");

                            i.putExtras(bundle);
                            startActivity(i);
                            /** Fading Transition Effect */
                            Cultural.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
