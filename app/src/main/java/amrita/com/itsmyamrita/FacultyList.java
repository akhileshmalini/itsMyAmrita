package amrita.com.itsmyamrita;

import android.content.ClipData;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.content.ContentValues.TAG;

/**
 * Created by Akhilesh on 12/27/2016.
 */

public class FacultyList extends AppCompatActivity {

    private DatabaseReference mDatabase,mFacultyRef,mDepartmentRef;
    FacultyAdapter mAdapter;
    private List<f_Faculty> facultyList = new ArrayList<>();
    ProgressBar progressBar;
    int a=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_listview);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.faclist);

        Bundle bundle = getIntent().getExtras();
        String dept  = bundle.getString("department","CSE");
            progressBar= (ProgressBar) findViewById(R.id.progressBar4);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFacultyRef = mDatabase.child("Faculty");
        mDepartmentRef = mFacultyRef.child(dept);
        mDepartmentRef.keepSynced(true);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(facultyList.size()==0){
                    if(isNetworkAvailable()){
                        while(a==5) {
                            Toast.makeText(getApplicationContext(), "Please Wait...Fetching", Toast.LENGTH_SHORT).show();
                        a+=1;
                        }
                            handler.postDelayed(this, 500);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
                        handler.postDelayed(this, 500);

                    }
                }
                else if(facultyList.size()!=0){
                    progressBar.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(this);
                }


            }
        }, 500);


        ValueEventListener post = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                facultyList.clear();
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    f_Faculty facultyDetails=child.getValue(f_Faculty.class);
                    facultyList.add(new f_Faculty(facultyDetails.getDesignation(),facultyDetails.getEmail(),facultyDetails.getImage(),facultyDetails.getNames(),facultyDetails.getQualification(),facultyDetails.getResearch()));
                }

                recyclerView.setAdapter(new FacultyAdapter(facultyList,getApplicationContext()));
                recyclerView.invalidate();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };
        mDepartmentRef.addValueEventListener(post);

        mAdapter = new FacultyAdapter(facultyList,getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}


class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.MyViewHolder> {

    private List<f_Faculty> facultyList;
    private Context context;
    public View v;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,designation,research,email,qualification;
        public ImageView imaga;


        public MyViewHolder(View view) {
            super(view);
            nama=(TextView) view.findViewById(R.id.textView2);
            designation = (TextView) view.findViewById(R.id.textView3);
            research = (TextView) view.findViewById(R.id.textView5);
            email=(TextView) view.findViewById(R.id.textView7);
            imaga=(ImageView) view.findViewById(R.id.icon);
            v=view;
        }
    }


    public FacultyAdapter(List<f_Faculty> facultyList, Context context) {
        this.facultyList = facultyList;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faculty_listitem, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final f_Faculty faculty = facultyList.get(position);
        holder.nama.setText(faculty.getNames()+", "+faculty.getResearch());
        holder.designation.setText(faculty.getDesignation());

        holder.research.setText("Research Interests: \n"+faculty.getQualification()+"\n\n");
        holder.email.setText(faculty.getEmail());
        Glide.with(context)

                .load(faculty.getImage())
                .bitmapTransform(new RoundedCornersTransformation(context,5,5))
                .into(holder.imaga);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"Copied Email to Clipboard",Toast.LENGTH_SHORT).show();
                // this api requires SDK version 11 and above, so suppress warning for now
                android.content.ClipboardManager clipboardMgr = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied text", faculty.getEmail().replaceAll("\\s+",""));
                clipboardMgr.setPrimaryClip(clip);
                return true;
            }
        });
    }





    @Override
    public int getItemCount() {
        return facultyList.size();
    }
}
