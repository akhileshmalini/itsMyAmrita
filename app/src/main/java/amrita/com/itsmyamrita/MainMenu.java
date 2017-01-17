package amrita.com.itsmyamrita;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yalantis.phoenix.PullToRefreshView;

import java.util.Calendar;

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
        String[] names={"Home","Forums","Faculty","Calendar","AHAN"};
        int[] imgs={R.drawable.home,R.drawable.clubs,R.drawable.teachers,R.drawable.calendars,R.drawable.ahans};
        list.setAdapter(new HomeAdapter(getApplicationContext(),names,imgs));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                if(position==0){
                    Intent i = new Intent(MainMenu.this, Home.class);
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

                if(position==3){
                    Intent i = new Intent(MainMenu.this, CalendarAct.class);
                    startActivity(i);

                    /** Fading Transition Effect */
                    MainMenu.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                if (position==4){
                    Uri uri = Uri.parse("https://asebmag.wordpress.com/"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    MainMenu.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }

            }
        });








    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_developers:
                Intent intent =new Intent(getApplicationContext(),Developer.class);
                startActivity(intent);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}


