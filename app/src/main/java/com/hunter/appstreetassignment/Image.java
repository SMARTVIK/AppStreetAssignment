package com.hunter.appstreetassignment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Image {

    private ArrayList<ImagesBean> images;

    public static Image objectFromData(String str) {

        return new Gson().fromJson(str, Image.class);
    }

    public ArrayList<ImagesBean> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean implements Parcelable {
        /**
         * id : 414
         * url : https://splashbase.s3.amazonaws.com/unsplash/regular/tumblr_mtax0twHix1st5lhmo1_1280.jpg
         * large_url : https://splashbase.s3.amazonaws.com/unsplash/large/1bqbiVH
         * source_id : 64
         */

        private int id;
        private String url;
        private String large_url;
        private int source_id;

        protected ImagesBean(Parcel in) {
            id = in.readInt();
            url = in.readString();
            large_url = in.readString();
            source_id = in.readInt();
        }

        public static final Creator<ImagesBean> CREATOR = new Creator<ImagesBean>() {
            @Override
            public ImagesBean createFromParcel(Parcel in) {
                return new ImagesBean(in);
            }

            @Override
            public ImagesBean[] newArray(int size) {
                return new ImagesBean[size];
            }
        };

        public static ImagesBean objectFromData(String str) {

            return new Gson().fromJson(str, ImagesBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLarge_url() {
            return large_url;
        }

        public void setLarge_url(String large_url) {
            this.large_url = large_url;
        }

        public int getSource_id() {
            return source_id;
        }

        public void setSource_id(int source_id) {
            this.source_id = source_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(url);
            parcel.writeString(large_url);
            parcel.writeInt(source_id);
        }
    }
}
