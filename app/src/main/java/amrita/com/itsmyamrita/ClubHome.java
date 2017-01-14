package amrita.com.itsmyamrita;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Akhilesh on 9/27/2016.
 */

public class ClubHome extends Fragment {

    private DatabaseReference mDatabase;
    private DatabaseReference mSectionRef, mClubRef,mHomeRef, mDescriptionRef,mMemberRef;
    Typeface custom_font1 ;
    ImageView bell,bellring;
    TextView beltext,belringtext;
    SharedPreferences saved_values;
    ProgressBar progressBar;
    ArrayList<f_Members> membersList = new ArrayList<f_Members>();
    ProgressBar pgBar;
    MemAdapter adapter;
    public static  int a=0;

    Handler handlers;

    Typeface custom_font;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_home, container, false);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        custom_font = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),  "fonts/Barrio-Regular.otf");
        custom_font1 = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),  "fonts/Quicksand-Regular.otf");


        Bundle args = getArguments();
        final String index = args.getString("club", "FACE");
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listmentor);
        saved_values = getActivity().getSharedPreferences("Dhara", Activity.MODE_PRIVATE);
        progressBar= (ProgressBar) view.findViewById(R.id.progressBar3);


//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        final TextView desc= (TextView) view.findViewById(R.id.txtDescp);
        final TextView t= (TextView) view.findViewById(R.id.textView);
        final TextView mentor= (TextView) view.findViewById(R.id.textView9);
        mentor.setTypeface(custom_font);

        boolean indexStatus = saved_values.getBoolean(index.toString(), false);  // false is the default value if nothing is returned.



        bell= (ImageView) view.findViewById(R.id.bell);
        bellring= (ImageView) view.findViewById(R.id.bellring);
        beltext= (TextView) view.findViewById(R.id.bellmsg);
        belringtext= (TextView) view.findViewById(R.id.bellringmsg);

        belringtext.setVisibility(View.INVISIBLE);
        bellring.setVisibility(View.INVISIBLE);
        bell.setVisibility(View.VISIBLE);
        beltext.setVisibility(View.VISIBLE);

        if (indexStatus){
            belringtext.setVisibility(View.VISIBLE);
            bellring.setVisibility(View.VISIBLE);
            bell.setVisibility(View.INVISIBLE);
            beltext.setVisibility(View.INVISIBLE);

        }else{


            belringtext.setVisibility(View.INVISIBLE);
            bellring.setVisibility(View.INVISIBLE);
            bell.setVisibility(View.VISIBLE);
            beltext.setVisibility(View.VISIBLE);

        }



        beltext.setTypeface(custom_font1);
        belringtext.setTypeface(custom_font1);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(desc.getText().toString().equals("Description")){
                    progressBar.setVisibility(View.VISIBLE);
                    handler.postDelayed(this, 500);

                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(null);

                }


            }
        }, 500);

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasActiveInternetConnection()) {

                    belringtext.setVisibility(View.VISIBLE);
                    bellring.setVisibility(View.VISIBLE);
                    bell.setVisibility(View.INVISIBLE);
                    beltext.setVisibility(View.INVISIBLE);
                    saved_values.edit().putBoolean(index.toString(), true).apply();

                    OneSignal.sendTag(index, "on");
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please Connect to internet to Subscribe", Toast.LENGTH_SHORT).show();
                }


            }
        });

        bellring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasActiveInternetConnection()) {

                    belringtext.setVisibility(View.INVISIBLE);
                bellring.setVisibility(View.INVISIBLE);
                bell.setVisibility(View.VISIBLE);
                beltext.setVisibility(View.VISIBLE);
                saved_values.edit().putBoolean(index.toString(), false).apply();
                OneSignal.deleteTag(index);
            }else {
                Toast.makeText(getActivity().getApplicationContext(), "Please Connect to internet to Un-Subscribe", Toast.LENGTH_SHORT).show();
            }

            }
        });







        t.setTypeface(custom_font);
        desc.setTypeface(custom_font1);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mSectionRef = mDatabase.child("Clubs");
        mClubRef = mSectionRef.child(index);
        mHomeRef = mClubRef.child("Home");
        mDescriptionRef = mHomeRef.child("description");
        mClubRef.keepSynced(true);

        mSectionRef = mDatabase.child("Clubs");
        mClubRef = mSectionRef.child(index);
        mMemberRef = mClubRef.child("Mentors");
        mMemberRef.keepSynced(true);


        ValueEventListener postListeners = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                membersList.clear();
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    f_Members memberDetails = child.getValue(f_Members.class);
                    membersList.add(new f_Members(memberDetails.getEmail(), memberDetails.getImage(), memberDetails.getNames(), memberDetails.getPhone(), memberDetails.getPosition()));
                }
try {
    recyclerView.setAdapter(new MemAdapter(membersList, getActivity().getApplicationContext()));
    recyclerView.invalidate();
}catch (Exception e){

}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };

        pgBar= (ProgressBar) view.findViewById(R.id.progressBar2);

        mMemberRef.addValueEventListener(postListeners);
        adapter = new MemAdapter(membersList,getActivity().getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);



        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    f_desc desco=child.getValue(f_desc.class);
                    desc.setText(desco.getDescription());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };
        mDescriptionRef.addValueEventListener(postListener);




    }


    private boolean isNetworkAvailable() {

        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }catch (Exception e){

        }
        return false;
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



class f_desc{
    String description;

    public f_desc(String description) {
        this.description = description;
    }

    public f_desc() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.MyViewHolder> {

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


    public MentorAdapter(List<f_Members> membersList, Context context) {
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
        holder.post.setText(members.getPosition());

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
