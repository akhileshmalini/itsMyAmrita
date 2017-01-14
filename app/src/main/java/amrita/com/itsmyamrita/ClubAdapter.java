package amrita.com.itsmyamrita;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akhilesh on 9/9/2016.
 */

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {

    private List<club> clubList;
    Context context;

    Typeface custom_font;
    Typeface custom_font1;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name,desc;
        public RelativeLayout back;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.itemname);
            desc=(TextView) view.findViewById(R.id.itemdesc);
            back= (RelativeLayout) view.findViewById(R.id.back);
        }
    }


    public ClubAdapter(Context context, String[] itemname, int[] img,String[] clubdesc) {
        this.context = context;
        clubList = new ArrayList<club>();
         custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/Barrio-Regular.otf");
         custom_font1 = Typeface.createFromAsset(context.getAssets(),  "fonts/Quicksand-Regular.otf");
        for (int i = 0; i < img.length; i++) {
            club item = new club(img[i], itemname[i], clubdesc[i]);
            clubList.add(item);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.club_listitem, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        club club = clubList.get(i);
        holder.name.setText(club.getItemName());
        holder.desc.setText(club.getDesc());
        Glide
                .with(context)
                .load(club.getImgs())
                .crossFade()
                .into(holder.image);

        holder.name.setTypeface(custom_font);
        holder.desc.setTypeface(custom_font1);

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
        return clubList.size();
    }
}

 class club {
    int imgs;
    String itemName, desc;
     public club() {
         this.imgs = R.drawable.aavishkara;//Some Default stuff
         this.itemName = "";
         this.desc = "";

     }

     club(int imgs, String itemName, String desc) {
        this.imgs = imgs;
        this.itemName = itemName;
        this.desc = desc;

    }


     public int getImgs() {
         return imgs;
     }

     public String getItemName() {
         return itemName;
     }

     public String getDesc() {
         return desc;
     }


     public void setImgs(int imgs) {
         this.imgs = imgs;
     }

     public void setItemName(String itemName) {
         this.itemName = itemName;
     }

     public void setDesc(String desc) {
         this.desc = desc;
     }
 }