package amrita.com.itsmyamrita;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Akhilesh on 7/21/2016.
 */

public class EventViewFragment extends AppCompatActivity {
    private View v;
    private ImageView imageView, call;
    private TextView txtperson, txtdate, txtdes, txtloc, txtonduty, txttitle,txt;
    private Toolbar toolbar;
    private CardView card;
    Typeface custom_font;
    Typeface custom_font1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_view);

        custom_font = Typeface.createFromAsset(getApplicationContext().getAssets(),  "fonts/Barrio-Regular.otf");
        custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(),  "fonts/Quicksand-Regular.otf");

        Bundle bundle = getIntent().getExtras();
        String person = bundle.getString("evContactPerson");
        final String Phone = bundle.getString("evContactPhone");
        String Date = bundle.getString("evDate");
        String Des = bundle.getString("evDescription");
        String Location = bundle.getString("evLocation");
        String name = bundle.getString("evName");
        String Onduty = bundle.getString("evOnDuty");
        String poster = bundle.getString("evPoster");
        String Time = bundle.getString("evTime");

        txttitle = (TextView) findViewById(R.id.txtEventTitle);
        txtperson = (TextView) findViewById(R.id.txtname);
        txtdate = (TextView) findViewById(R.id.txtEvDateTime);
        txtdes = (TextView) findViewById(R.id.txtEvDescp);
        txtonduty = (TextView) findViewById(R.id.txtonDuty);
        card= (CardView) findViewById(R.id.cardView);
        txt = (TextView) findViewById(R.id.txt);

        txttitle.setTypeface(custom_font);
        txtperson.setTypeface(custom_font);
        txtdate.setTypeface(custom_font1);
        txtdes.setTypeface(custom_font1);
        txtonduty.setTypeface(custom_font1);
        txt.setTypeface(custom_font);


        txttitle.setText(name);
        txtperson.setText(person);
        txtdate.setText(Date + " | " + Time+" @"+Location);
        txtdes.setText(Des);
        if(Onduty.equals("Provided")){
            txtonduty.setText("On Duty Leave is " + Onduty);

        }
        else if(Onduty.equals("NotProvided")){
            txtonduty.setText("On Duty Leave is Not Provided");

        }
        else{
            txtonduty.setText("On Duty Leave is " + Onduty);

        }
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+Phone));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(name);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        imageView= (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(poster).into(imageView);

        setToolbar();

    }

    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24px);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });



    }

}
