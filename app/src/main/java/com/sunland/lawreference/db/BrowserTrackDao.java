package com.sunland.lawreference.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BrowserTrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BrowserTrackBean browserTrackBean);

    @Delete
    void delete(BrowserTrackBean browserTrackBean);

    @Query("SELECT * FROM browserTrack ORDER BY timeStamp desc LIMIT 20")
    List<BrowserTrackBean> getList();

    @Query("DELETE FROM browserTrack")
    void deleteAllRecords();

}
