package amrita.com.itsmyamrita;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

/**
 * Created by Akhilesh on 1/14/2017.
 */

public class CalendarAct extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference mSectionRef;
    ArrayList<f_Calendar> calendarList = new ArrayList<f_Calendar>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        final String[] MonthsImg={"a","a","a","a","a","a","a","a","a","a","a","a"};
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mSectionRef = mDatabase.child("Calendar");


        String[] names={
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",

        };
        Calendar c = Calendar.getInstance();
        int month =c.get(Calendar.MONTH);
        int year =c.get(Calendar.YEAR);
        final TextView months = (TextView) findViewById(R.id.textView11);
        TextView years = (TextView) findViewById(R.id.textView13);
        months.setText(""+names[month]);
        years.setText(""+year);


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                calendarList.clear();
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    f_Calendar calendarDetails = child.getValue(f_Calendar.class);
                    calendarList.add(new f_Calendar(calendarDetails.getMonths(),calendarDetails.getImage()));
                }
                for(f_Calendar cal :calendarList){
                    switch (cal.getMonths()){
                        case "January":MonthsImg[0]=cal.getImage();break;
                        case "February":MonthsImg[1]=cal.getImage();break;
                        case "March":MonthsImg[2]=cal.getImage();break;
                        case "April":MonthsImg[3]=cal.getImage();break;
                        case "May":MonthsImg[4]=cal.getImage();break;
                        case "June":MonthsImg[5]=cal.getImage();break;
                        case "July":MonthsImg[6]=cal.getImage();break;
                        case "August":MonthsImg[7]=cal.getImage();break;
                        case "September":MonthsImg[8]=cal.getImage();break;
                        case "October":MonthsImg[9]=cal.getImage();break;
                        case "November":MonthsImg[10]=cal.getImage();break;
                        case "December":MonthsImg[11]=cal.getImage();break;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };


        mSectionRef.addValueEventListener(postListener);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.calends);


        final CalendarAdapter adapter =new CalendarAdapter(getApplicationContext(),names);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(calendarList.size()==0){
                   if(isNetworkAvailable()){
                       Toast.makeText(getApplicationContext(),"Please Wait...Fetching",Toast.LENGTH_SHORT).show();
                       handler.postDelayed(this, 500);

                   }
                    else{
                       Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
                       handler.postDelayed(this, 500);

                   }
                }
                else if(calendarList.size()==12){
                    Toast.makeText(getApplicationContext(),"Calendar is Available",Toast.LENGTH_SHORT).show();

                    handler.removeCallbacks(this);
                }


            }
        }, 500);



        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i;
                        Bundle bundle = new Bundle();


                        if (position == 0) {
                            if(MonthsImg[0].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();

                            }else{
                                if(!(MonthsImg[0].length()>=10)){

                                }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[0]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }


                            }


                        }
                        else if (position == 1) {
                            if(MonthsImg[1].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();

                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[1]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 2) {
                            if(MonthsImg[2].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();

                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[2]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 3) {
                            if(MonthsImg[3].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();

                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[3]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 4) {
                            if(MonthsImg[4].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();

                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[4]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 5) {
                            if(MonthsImg[5].equals("NA")){
                            Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[5]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 6) {
                            if(MonthsImg[6].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[6]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 7) {
                            if(MonthsImg[7].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[7]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 8) {
                            if(MonthsImg[8].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[8]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 9) {
                            if(MonthsImg[9].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[9]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 10) {
                            if(MonthsImg[10].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[10]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }
                        else if (position == 11) {
                            if(MonthsImg[11].equals("NA")){
                                Toast.makeText(getApplicationContext(),"Month not available this Semester",Toast.LENGTH_SHORT).show();
                            }else{if(!(MonthsImg[0].length()>=10)){
                                Toast.makeText(getApplicationContext(),"Please Connect to internet to Fetch!",Toast.LENGTH_SHORT).show();

                            }else{
                                i = new Intent(CalendarAct.this, Calview.class);
                                i.putExtra("Month",MonthsImg[11]);
                                startActivity(i);
                                /** Fading Transition Effect */
                                CalendarAct.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            }}


                        }






                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                })
        );


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean hasActiveInternetConnection() {
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e("LOG", "Error checking internet connection", e);
            }
        } else {
            Log.d("LOG", "No network available!");
        }
        return false;
    }

}
