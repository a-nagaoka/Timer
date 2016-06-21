package com.example.yoshizawa.stampapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

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

        long stampCnt = realm.where(StampCard.class).count();

        long nextId = 1L;
        realm.beginTransaction();

        // 20個が最大
        if(stampCnt > 19) {
            realm.where(StampCard.class).findAll().clear();
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

        RealmResults<StampCard> stampCards = realm.where(StampCard.class).findAll();

        StampAdapter stampAdapter = new StampAdapter(this,stampCards,true);
        mGridView.setAdapter(stampAdapter);

        if(stampCards.size() == 10){
            callAlert("10個たまったね！もうすこし！");
        }
        if(stampCards.size() == 20){
            callAlert("20個たまったね！おめでとう！");
        }

    }

    private void callAlert(String msg) {
        CustomDialog customDialog = new CustomDialog();
        customDialog.setMessage(msg);
        customDialog.show(getFragmentManager(),"カスタム");
    }

}
