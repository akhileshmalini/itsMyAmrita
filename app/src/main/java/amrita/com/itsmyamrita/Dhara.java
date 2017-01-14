package amrita.com.itsmyamrita;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Akhilesh on 1/12/2017.
 */

public class Dhara extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private DatabaseReference mSectionRef, mClubRef, mMemberRef,mMentorRef;
    ArrayList<f_Members> membersList = new ArrayList<f_Members>();
    TextView txt1,txt2;
    ArrayList<f_Members> mentorList = new ArrayList<f_Members>();

    MemAdapter adapter,Facadapter;
    public static  int a=0,b=0;

    Typeface custom_font;

    ProgressBar pgBar,pgBar2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhara);



        mDatabase = FirebaseDatabase.getInstance().getReference();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.membersDhara);
        final RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.facultyDhara);

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Barrio-Regular.otf");



        txt1= (TextView) findViewById(R.id.textView6);
        txt2= (TextView) findViewById(R.id.textView8);


        txt1.setTypeface(custom_font);
        txt2.setTypeface(custom_font);

        pgBar= (ProgressBar) findViewById(R.id.progressBar3);







        mSectionRef = mDatabase.child("Clubs");
        mClubRef = mSectionRef.child("Dhara");
        mMemberRef = mClubRef.child("Members");
        mMemberRef.keepSynced(true);
        mMentorRef = mClubRef.child("Mentors");
        mMentorRef.keepSynced(true);


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                membersList.clear();
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    f_Members memberDetails = child.getValue(f_Members.class);
                    membersList.add(new f_Members(memberDetails.getEmail(), memberDetails.getImage(), memberDetails.getNames(), memberDetails.getPhone(), memberDetails.getPosition()));
                }
                recyclerView.setAdapter(new MemAdapter(membersList, getApplicationContext()));
                recyclerView.invalidate();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };



        mMemberRef.addValueEventListener(postListener);
        adapter = new MemAdapter(membersList,getApplicationContext());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (membersList.size() == 0) {
                    if(isNetworkAvailable()) {
                        pgBar.setVisibility(View.VISIBLE);
                        handler.postDelayed(this, 500);

                    }else {

                        if (a == 0) {
                            a += 1;
                            Toast.makeText(getApplicationContext(), "Please Note, You only have to connect to the internet once. Once the Data is Synced, it will be available offline!", Toast.LENGTH_SHORT).show();
                        }
                        pgBar.setVisibility(View.INVISIBLE);
                        handler.postDelayed(this, 500);
                    }
                }else {

                    pgBar.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(null);
                }


            }
        }, 500);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        ValueEventListener postListeners = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mentorList.clear();
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    f_Members memberDetails = child.getValue(f_Members.class);
                    mentorList.add(new f_Members(memberDetails.getEmail(), memberDetails.getImage(), memberDetails.getNames(), memberDetails.getPhone(), memberDetails.getPosition()));
                }
                recyclerView2.setAdapter(new MemAdapter(mentorList, getApplicationContext()));
                recyclerView2.invalidate();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };

        pgBar2= (ProgressBar) findViewById(R.id.progressBar3);

        mMentorRef.addValueEventListener(postListeners);
        Facadapter = new MemAdapter(mentorList,getApplicationContext());

        final Handler handlers = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mentorList.size() == 0) {
                    if(isNetworkAvailable()) {
                        pgBar2.setVisibility(View.VISIBLE);
                        handlers.postDelayed(this, 500);

                    }else {
                        if (b == 0) {
                            b += 1;
                            Toast.makeText(getApplicationContext(), "Please Note, You only have to connect to the internet once. Once the Data is Synced, it will be available offline!", Toast.LENGTH_SHORT).show();
                        }
                        pgBar2.setVisibility(View.INVISIBLE);
                        handlers.postDelayed(this, 500);
                    }
                }else {
                    pgBar2.setVisibility(View.INVISIBLE);
                    handlers.removeCallbacks(null);
                }


            }
        }, 500);
        RecyclerView.LayoutManager mLayoutManagers = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManagers);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(Facadapter);






    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


class DharaAdapter extends RecyclerView.Adapter<DharaAdapter.MyViewHolder> {

    private List<f_Members> membersList;
    private Context context;
    public View v;
    Typeface custom_font,custom_font1 ;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,post;
        Button call,email;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.imageView);
            name = (TextView) view.findViewById(R.id.txtname);
            post = (TextView) view.findViewById(R.id.txtpost);
            call= (Button) view.findViewById(R.id.button3);
            email=(Button) view.findViewById(R.id.button2);
            v=view;
        }
    }


    public DharaAdapter(List<f_Members> membersList, Context context) {
        this.membersList = membersList;
        this.context=context;
        custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/Barrio-Regular.otf");
        custom_font1 = Typeface.createFromAsset(context.getAssets(),  "fonts/Quicksand-Regular.otf");


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bearer_listitem, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final f_Members members = membersList.get(position);
        holder.name.setTypeface(custom_font);
        holder.post.setTypeface(custom_font1);
        Glide
                .with(context)
                .load(members.getImage())
                .into(holder.image);
        holder.name.setText(members.getNames());
        String p=members.getPosition().toString().replaceAll(":", "\n");
        System.out.println(p);
        holder.post.setText(p);



        if(members.getEmail().replaceAll("\\s+","").equals("")){
            holder.email.setVisibility(View.INVISIBLE);
        }else{
            holder.email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Copied Email to Clipboard",Toast.LENGTH_SHORT).show();
                    // this api requires SDK version 11 and above, so suppress warning for now
                    android.content.ClipboardManager clipboardMgr = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied text", members.getEmail().replaceAll("\\s+",""));
                    clipboardMgr.setPrimaryClip(clip);
                }
            });
        }

        if(members.getPhone().replaceAll("\\s+","").equals("")){
            holder.call.setVisibility(View.INVISIBLE);

        }else{
            holder.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Copied Phone Number to Clipboard",Toast.LENGTH_SHORT).show();
                    // this api requires SDK version 11 and above, so suppress warning for now
                    android.content.ClipboardManager clipboardMgr = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied text", members.getPhone().replaceAll("\\s+",""));
                    clipboardMgr.setPrimaryClip(clip);
                }
            });
        }




    }





    @Override
    public int getItemCount() {
        return membersList.size();
    }
}
