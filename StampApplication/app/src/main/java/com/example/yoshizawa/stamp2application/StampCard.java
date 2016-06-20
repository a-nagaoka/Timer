package com.example.yoshizawa.stamp2application;

import io.realm.RealmObject;

/**
 * Created by yoshizawa on 2016/06/19.
 */
public class StampCard extends RealmObject {

    private long id;

    private Integer inStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Integer getInStamp() {
        return inStamp;
    }

    public void setInStamp(Integer inStamp) {
        this.inStamp = inStamp;
    }
}
