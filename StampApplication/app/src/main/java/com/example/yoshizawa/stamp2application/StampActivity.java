package com.example.yoshizawa.stamp2application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class StampActivity extends AppCompatActivity {

    private GridView mGridView;

    private  Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        mGridView = (GridView) findViewById(R.id.stampList);

        realm = Realm.getInstance(this);

        RealmResults<StampCard> stampCards = realm.where(StampCard.class).findAll();

        long nextId = 1L;
        realm.beginTransaction();
        stampCards = realm.where(StampCard.class).findAll();
        // 20個が最大
        if(stampCards.size() > 19) {
            stampCards.clear();
        }
        StampCard stamp = realm.createObject(StampCard.class);
        Number maxId = realm.where(StampCard.class).max("id");
        if (maxId == null) {
            stamp.setId(nextId);
        } else {
            stamp.setId(maxId.longValue() + 1);
        }
        stamp.setInStamp(1);
        realm.commitTransaction();

        stampCards = realm.where(StampCard.class).findAll();

        StampAdapter stampAdapter = new StampAdapter(this,stampCards,true);
        mGridView.setAdapter(stampAdapter);

        if(stampCards.size() == 10){
            Toast.makeText(this,"10個貯まりました！",Toast.LENGTH_SHORT).show();
        }
        if(stampCards.size() == 20){
            Toast.makeText(this,"おめでとう！20個たまったね！",Toast.LENGTH_SHORT).show();
        }

    }

}
