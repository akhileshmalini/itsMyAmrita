package amrita.com.itsmyamrita;

/**
 * Created by Akhilesh on 12/30/2016.
 */

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


public class ClubMain extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView,tabBg;
    private CollapsingToolbarLayout collapsingToolbar;
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    String clubs,clubdes;
    String Image;
    ProgressBar progressBar;

    private DatabaseReference mDatabase;
    private DatabaseReference mSectionRef, mClubRef,mHomeRef,mcoverRef;
    TextView title,titledes;
    Typeface custom_font,custom_font1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        clubs = bundle.getString("department","FACE");
        clubdes=bundle.getString("clubs","FACE");
        setContentView(R.layout.club_new_main);

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Barrio-Regular.otf");
        custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Quicksand-Regular.otf");
        title= (TextView) findViewById(R.id.txtTitle);
        titledes= (TextView) findViewById(R.id.txtTitleDes);
        title.setTypeface(custom_font);
        titledes.setTypeface(custom_font1);
        title.setText(clubs);
        titledes.setText(clubdes);



        imageView= (ImageView) findViewById(R.id.backdrop);
        tabBg= (ImageView) findViewById(R.id.tabBg);
        collapsingToolbar=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mSectionRef = mDatabase.child("Clubs");
        mClubRef = mSectionRef.child(clubs);
        mHomeRef = mClubRef.child("Home");
        mcoverRef= mHomeRef.child("Image");






        ValueEventListener postListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    f_Cover descos=child.getValue(f_Cover.class);
                    Image=descos.getImage();
                    setImage(Image);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        };
        mcoverRef.addValueEventListener(postListener1);







        setToolbar();

        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTabLayout= (TabLayout) findViewById(R.id.detail_tabs);
        tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);







        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    @Override
    public void onBackPressed() {
            super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }


    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setImage(String image) {
        Glide.with(this).load(image).into(imageView);
    }

    class TabPagerAdapter extends FragmentPagerAdapter {

        public TabPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();

            if (position == 0) {
                ClubHome clubHomeFragment = new ClubHome();
                args.putString("club", clubs);
                clubHomeFragment.setArguments(args);
                return clubHomeFragment;
            } else if (position == 1) {
                ClubMember clubMembersFragment = new ClubMember();
                args.putString("club", clubs);
                clubMembersFragment.setArguments(args);
                return clubMembersFragment;
            } else if (position == 2) {
                ClubEvent clubEventsFragment = new ClubEvent();
                args.putString("club", clubs);
                clubEventsFragment.setArguments(args);
                return clubEventsFragment;
            }
            ClubMember clubMembersFragment = new ClubMember();
            return clubMembersFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Members";
                case 2:
                    return "Events";


            }
            return "Title";
        }
    }
}

 class f_Cover{
    String image;

    public f_Cover(String image) {
        this.image = image;
    }

    public f_Cover() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}