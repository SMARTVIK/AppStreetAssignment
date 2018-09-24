package com.hunter.appstreetassignment.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ImageDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insert(Image word);

   @Query("DELETE FROM image_table")
   void deleteAll();

   public String tag="";

   @Query("SELECT * from image_table")
   LiveData<List<Image>> getAllImages();

   @Delete
   void deleteImage(Image image);

   @Update(onConflict = OnConflictStrategy.REPLACE)
   void update(Image word);

   @Query("SELECT * FROM image_table WHERE tag LIKE :searchQuery")
   LiveData<List<Image>> getImagesStartingWith(String searchQuery);
}