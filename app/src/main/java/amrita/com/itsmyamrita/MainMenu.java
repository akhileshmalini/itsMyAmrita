package amrita.com.itsmyamrita;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yalantis.phoenix.PullToRefreshView;

/**
 * Created by Akhilesh on 9/9/2016.
 */

public class MainMenu extends AppCompatActivity {
    private PullToRefreshView mPullToRefreshView;
    int REFRESH_DELAY = 2;
    ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);

        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });

        list= (ListView) findViewById(R.id.list_view);
        String[] names={"Home","Forums","Faculty"};
        int[] imgs={R.drawable.home,R.drawable.clubs,R.drawable.teachers};
        list.setAdapter(new HomeAdapter(getApplicationContext(),names,imgs));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                if(position==0){
                    Intent i = new Intent(MainMenu.this, ClubMain.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    MainMenu.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==1){
                    Intent i = new Intent(MainMenu.this, Forums.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    MainMenu.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if(position==2){
                    Intent i = new Intent(MainMenu.this, Faculty.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    MainMenu.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }
        });


    }



}


