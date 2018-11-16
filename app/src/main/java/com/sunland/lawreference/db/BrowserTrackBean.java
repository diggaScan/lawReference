package com.sunland.lawreference.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "browserTrack")
public class BrowserTrackBean {

    @ColumnInfo
    @NonNull
    @PrimaryKey
    public String fileNameCN;

    @ColumnInfo
    public String fileNameEN;

    @ColumnInfo
    public long timeStamp;
}
