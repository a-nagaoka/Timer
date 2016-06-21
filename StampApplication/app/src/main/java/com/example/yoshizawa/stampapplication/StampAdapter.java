package com.example.yoshizawa.stampapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by yoshizawa on 2016/06/19.
 */
    public class StampAdapter extends RealmBaseAdapter<StampCard> {

    private static class ViewHolder{
        ImageView imageView;
    }

    public StampAdapter(Context context, RealmResults<StampCard> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.hue_imageview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        StampCard stampCard = realmResults.get(position);
        viewHolder.imageView.setImageResource(R.drawable.good3);


        return convertView;
    }
}
