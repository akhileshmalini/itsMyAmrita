package amrita.com.itsmyamrita;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static amrita.com.itsmyamrita.R.id.itemname;
import static amrita.com.itsmyamrita.R.layout.calendar;

/**
 * Created by Akhilesh on 1/14/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> {

        private List<calendar> calendarList;
        Context context;

        Typeface custom_font;
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public RelativeLayout back;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(itemname);
                back= (RelativeLayout) view.findViewById(R.id.back);
            }
        }


        public CalendarAdapter(Context context, String[] itemname) {
            this.context = context;
            calendarList = new ArrayList<calendar>();
            custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/Barrio-Regular.otf");
            for (int i = 0; i < itemname.length; i++) {
                calendar item = new calendar( itemname[i]);
                calendarList.add(item);

            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.calendar_listitem, parent, false);

            return new MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int i) {
            calendar calendar = calendarList.get(i);
            holder.name.setText(calendar.getItemName());


            holder.name.setTypeface(custom_font);

            if(i==0 ||i==5||i==10||i==15){
                holder.back.setBackgroundColor(Color.parseColor("#c14f4c"));
            }
            if(i==1||i==6||i==11||i==16){
                holder.back.setBackgroundColor(Color.parseColor("#f6d93c"));
            }
            if(i==2||i==7||i==12||i==17){
                holder.back.setBackgroundColor(Color.parseColor("#E0A025"));
            }
            if(i==3||i==8||i==13||i==18){
                holder.back.setBackgroundColor(Color.parseColor("#006495"));
            }
            if(i==4||i==9||i==14||i==19){
                holder.back.setBackgroundColor(Color.parseColor("#004C70"));
            }
        }

        @Override
        public int getItemCount() {
            return calendarList.size();
        }


    }

class calendar {
    String itemName;

    public calendar() {
        this.itemName = "";


    }

    calendar(String itemName) {
        this.itemName = itemName;

    }

    public String getItemName() {
        return itemName;
    }

}

