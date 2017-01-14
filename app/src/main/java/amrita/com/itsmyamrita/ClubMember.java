package amrita.com.itsmyamrita;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.content.ContentValues.TAG;

/**
 * Created by Akhilesh on 9/23/2016.
 */

public class ClubMember extends Fragment {


    private DatabaseReference mDatabase;
    private DatabaseReference mSectionRef, mClubRef, mMemberRef;
    ArrayList<f_Members> membersList = new ArrayList<f_Members>();
    MemAdapter adapter;
    public static  int a=0;
    ImageView fleur;
    TextView txt;
    Typeface custom_font;

    ProgressBar pgBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_members, container, false);
       try {
           mDatabase = FirebaseDatabase.getInstance().getReference();
           final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listmem);
           custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Barrio-Regular.otf");

           Bundle args = getArguments();
           String index = args.getString("club", "FACE");


           fleur = (ImageView) view.findViewById(R.id.imageView3);
           txt = (TextView) view.findViewById(R.id.txtnoevent);
           pgBar = (ProgressBar) view.findViewById(R.id.progressBar2);


           mSectionRef = mDatabase.child("Clubs");
           mClubRef = mSectionRef.child(index);
           mMemberRef = mClubRef.child("Members");
           mMemberRef.keepSynced(true);


           ValueEventListener postListener = new ValueEventListener() {
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

}catch (Exception es){

}


               }

               @Override
               public void onCancelled(DatabaseError databaseError) {
                   // Getting Post failed, log a message
                   Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

               }
           };


           mMemberRef.addValueEventListener(postListener);
           adapter = new MemAdapter(membersList, getActivity().getApplicationContext());
           txt.setTypeface(custom_font);

           final Handler handler = new Handler();
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {

                   if (membersList.size() == 0) {
                       if (isNetworkAvailable()) {
                           txt.setText("Fetching from the Interwebs...\n Shouldn't take long!");
                           fleur.setVisibility(View.VISIBLE);
                           txt.setVisibility(View.VISIBLE);
                           pgBar.setVisibility(View.VISIBLE);
                           handler.postDelayed(this, 500);

                       } else {
                           fleur.setVisibility(View.VISIBLE);
                           txt.setText("No Internet Connection Available\n Please Connect to fetch data");
                           if (a == 0) {
                               a += 1;
                                try {
                                    Toast.makeText(getActivity().getApplicationContext(), "Please Note, You only have to connect to the internet once. Once the Data is Synced, it will be available offline!", Toast.LENGTH_SHORT).show();

                                }catch (Exception e){

                                }
                               }
                           txt.setVisibility(View.VISIBLE);
                           pgBar.setVisibility(View.INVISIBLE);
                           handler.postDelayed(this, 500);
                       }
                   } else {
                       fleur.setVisibility(View.INVISIBLE);
                       txt.setVisibility(View.INVISIBLE);
                       pgBar.setVisibility(View.INVISIBLE);
                       handler.removeCallbacks(null);
                   }


               }
           }, 500);
           RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
           recyclerView.setLayoutManager(mLayoutManager);
           recyclerView.setItemAnimator(new DefaultItemAnimator());
           recyclerView.setAdapter(adapter);
       }catch (Exception e){

       }


        return view;
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

}

class MemAdapter extends RecyclerView.Adapter<MemAdapter.MyViewHolder> {

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


    public MemAdapter(List<f_Members> membersList, Context context) {
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
