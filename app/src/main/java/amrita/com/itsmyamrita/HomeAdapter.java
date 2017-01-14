package amrita.com.itsmyamrita;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Akhilesh on 9/9/2016.
 */

public class HomeAdapter extends BaseAdapter {
    //Declarations
    ArrayList<singleHomeItem> list;
    Context context;
    String[] itemname;
    int[] imgs;
    Typeface custom_font ;
    //Constructor
    public HomeAdapter(Context context, String[] itemname, int[] img) {
        this.context = context;
        list = new ArrayList<singleHomeItem>();
        this.itemname = itemname;
        this.imgs = img;
        custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/Barrio-Regular.otf");
        for (int i = 0; i < imgs.length; i++) {
            singleHomeItem item = new singleHomeItem(imgs[i], itemname[i]);
            list.add(item);

        }

    }


    public int getCount() {
        return list.size();
    }

    public Object getItem(int i) {
        return list.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        ImageView image;
        TextView name;

        ViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.itemname);

        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        View row = view;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.home_listitem, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        if(i==0 ||i==5||i==10||i==15){
            row.setBackgroundColor(Color.parseColor("#c14f4c"));
        }
        if(i==1||i==6||i==11||i==16){
            row.setBackgroundColor(Color.parseColor("#f6d93c"));
        }
        if(i==2||i==7||i==12||i==17){
            row.setBackgroundColor(Color.parseColor("#E0A025"));
        }
        if(i==3||i==8||i==13||i==18){
            row.setBackgroundColor(Color.parseColor("#006495"));
        }
        if(i==4||i==9||i==14||i==19){
            row.setBackgroundColor(Color.parseColor("#004C70"));
        }



        holder.name.setTypeface(custom_font);
        singleHomeItem temp = list.get(i);
        Glide
                .with(context)
                .load(temp.imgs)
                .crossFade()
                .into(holder.image);
        holder.name.setText(temp.itemName);


        return row;
    }

    static class singleHomeItem {
        int imgs;
        String itemName;

        singleHomeItem(int imgs, String itemName) {
            this.imgs = imgs;
            this.itemName = itemName;

        }
    }


}
