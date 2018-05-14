package com.popularmoviestage1;

/**
 * Created by Umasree D on 1/5/2018
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieImageAdapter extends BaseAdapter {
    private Context mContext;

    public MovieImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount(){
        return images.size();
    }

    @Override
    public Object getItem(int position){
        return images.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageview;
        if (convertView == null){
            imageview = new ImageView(mContext);
            imageview.setPadding(0, 0, 0, 0);
            imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageview.setAdjustViewBounds(true);
        } else {
            imageview = (ImageView) convertView;
        }

        Picasso.with(mContext).load(images.get(position)).placeholder(R.mipmap.ic_launcher).into(imageview);
        return imageview;
    }


    public void addItem(String url){
        images.add(url);
    }

    public void clearItems() {
        images.clear();
    }

    public ArrayList<String> images = new ArrayList<String>();
}



