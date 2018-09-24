package com.hunter.appstreetassignment.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "image_table")
public class Image implements Parcelable{
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @ColumnInfo(name = "tag")
    private  String tag;

    public Image() {

    }

    protected Image(Parcel in) {
        tag = in.readString();
        id = in.readString();
        imagePath = in.readString();
        isUploaded = in.readByte() != 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")

    private String id;

    @ColumnInfo(name = "imagePath")
    private String imagePath;

    @ColumnInfo(name = "isUploaded")
    private boolean isUploaded;

    public Image(String id, String word, boolean isUploaded,String query) {
        this.id = id;
        this.imagePath = word;
        this.isUploaded = isUploaded;
        this.tag = query;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tag);
        parcel.writeString(id);
        parcel.writeString(imagePath);
        parcel.writeByte((byte) (isUploaded ? 1 : 0));
    }
}