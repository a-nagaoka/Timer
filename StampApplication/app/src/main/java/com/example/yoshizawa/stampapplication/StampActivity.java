package com.example.yoshizawa.stampapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class StampActivity extends AppCompatActivity {

    private GridView mGridView;

    private  Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        Intent intent = getIntent();
        long id = intent.getLongExtra("ID",1L);

        mGridView = (GridView) findViewById(R.id.stampList);

        // テーブル構造など変更があった場合のテーブル初期化
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
//        realm.deleteRealm(realmConfig);

        realm = Realm.getInstance(this);

        RealmQuery<StampCard> stampCardQuery = realm.where(StampCard.class).equalTo("id",id);

        long stampCnt = stampCardQuery.count();

        realm.beginTransaction();

        // 20個が最大
        if(stampCnt > 19) {
            stampCardQuery.findAll().clear();
        }

        StampCard stamp = realm.createObject(StampCard.class);
        Number maxStampId = stampCardQuery.max("inStamp");
        stamp.setId(id);
        if (maxStampId == null) {
            stamp.setInStamp(1);
        } else {

            stamp.setInStamp(maxStampId.intValue() + 1);
        }

        realm.commitTransaction();

        RealmResults<StampCard> stampCards = stampCardQuery.findAll();

        StampAdapter stampAdapter = new StampAdapter(this,stampCards,true);
        mGridView.setAdapter(stampAdapter);

        if(stampCards.size() == 10){
            callAlert("10個たまったね！もうすこし！", 1);
        }
        if(stampCards.size() == 20){
            callAlert("20個たまったね！おめでとう！", 2);
            ImageView happyView = (ImageView)findViewById(R.id.happyView);
            happyView.setImageResource(R.drawable.music);
        }

    }

    private void callAlert(String msg, int type) {
        CustomDialog customDialog = new CustomDialog();
        customDialog.setMessage(msg);
        if(type == 1){
            customDialog.setImage(R.drawable.donut);
        }else if(type == 2){
            customDialog.setImage(R.drawable.eclair);
        }
        customDialog.show(getFragmentManager(),"カスタム");
    }

}
