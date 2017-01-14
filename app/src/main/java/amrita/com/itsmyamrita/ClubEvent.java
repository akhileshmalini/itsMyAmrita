package amrita.com.itsmyamrita;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Akhilesh on 9/27/2016.
 */

public class ClubEvent extends Fragment {

    private DatabaseReference mDatabase,mClubRef,mSectionRef,mEventRef;
    EventsAdapter mAdapter;
    private List<f_Events> eventsList = new ArrayList<>();

    TextView noev;
    ImageView img;

    Typeface custom_font;
    SwipeRefreshLayout mSwipeRefreshLayout;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.club_events,container,false);


        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.eveList);
        Bundle args = getArguments();
        String index = args.getString("club", "FACE");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mSectionRef = mDatabase.child("Clubs");
        mClubRef = mSectionRef.child(index);
        mEventRef = mClubRef.child("Events");
        mClubRef.keepSynced(true);


        noev= (TextView) view.findViewById(R.id.txtnoevent);
        img= (ImageView) view.findViewById(R.id.imageView3);


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Barrio-Regular.otf");
        noev.setTypeface(custom_font);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventsList.clear();
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    f_Events eventDetails=child.getValue(f_Events.class);
                    eventsList.add(new f_Events(eventDetails.getEvContactPerson(),eventDetails.getEvContactPhone(),eventDetails.getEvDate(),eventDetails.getEvDescription(),eventDetails.getEvLocation(),eventDetails.getEvName(),eventDetails.getEvOnDuty(),eventDetails.getEvPoster(),eventDetails.getEvTime()));
                }
                if (eventsList.size()==0){
                    noev.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);
                }else
                {
                    noev.setVisibility(View.INVISIBLE);
                    img.setVisibility(View.INVISIBLE);
                }

                recyclerView.setAdapter(new EventsAdapter(eventsList,getActivity().getApplicationContext()));
                recyclerView.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
    }
};

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                 Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 2000);
                if(!isNetworkAvailable()){

                    Toast.makeText(getActivity().getApplicationContext(),"Connect to Internet to check for Fresh Events!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"No New Events Available!",Toast.LENGTH_SHORT).show();
                }
            }
        });



        mEventRef.addValueEventListener(postListener);
        mAdapter = new EventsAdapter(eventsList,getActivity().getApplicationContext());


        if(!isNetworkAvailable()){
            Toast.makeText(getActivity().getApplicationContext(),"Connect to Internet to check for Fresh Content!",Toast.LENGTH_SHORT).show();
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return view;
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}




class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private List<f_Events> eventsList;
    private Context context;
    Typeface custom_font;
    Typeface custom_font1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,timedate,read;
        public ImageView pic,like,share,calender;


        public MyViewHolder(View view) {
            super(view);
            timedate=(TextView) view.findViewById(R.id.txtDateTime);
            title = (TextView) view.findViewById(R.id.txtEvTitle);
            read = (TextView) view.findViewById(R.id.textView4);

            pic = (ImageView) view.findViewById(R.id.mediaArea);

        }
    }


    public EventsAdapter(List<f_Events> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context=context;
        custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/Barrio-Regular.otf");
        custom_font1 = Typeface.createFromAsset(context.getAssets(),  "fonts/Quicksand-Regular.otf");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_listitem, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final f_Events events = eventsList.get(position);
        holder.title.setTypeface(custom_font);
        holder.timedate.setTypeface(custom_font1);
        holder.read.setTypeface(custom_font1);

        holder.title.setText(events.getEvName());
        holder.timedate.setText(events.getEvTime()+" | "+events.getEvDate());
        holder. title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,EventViewFragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //Create the bundle
                Bundle bundle = new Bundle();
                //Add your data from getFactualResults method to bundle
                bundle.putString("evContactPerson", events.getEvContactPerson());
                bundle.putString("evContactPhone", events.getEvContactPhone());
                bundle.putString("evDate", events.getEvDate());
                bundle.putString("evDescription", events.getEvDescription());
                bundle.putString("evLocation", events.getEvLocation());
                bundle.putString("evName", events.getEvName());
                bundle.putString("evOnDuty", events.getEvOnDuty());
                bundle.putString("evPoster", events.getEvPoster());
                bundle.putString("evTime", events.getEvTime());
                //Add the bundle to the intent
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });
        Glide.with(context)
                .load(events.getEvPoster())
                .placeholder(R.drawable.train)
                .error(R.drawable.train)
//                .centerCrop()
                .into(holder.pic);










    }





    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
