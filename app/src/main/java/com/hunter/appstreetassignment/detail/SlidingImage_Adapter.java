package com.hunter.appstreetassignment.detail;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hunter.appstreetassignment.R;
import com.hunter.appstreetassignment.simplegallerycaching.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SlidingImage_Adapter extends PagerAdapter {

    private ArrayList<ImageModel.HitsBean> images;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImage_Adapter(Context context, ArrayList<ImageModel.HitsBean> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }
 
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
 
    @Override
    public int getCount() {
        return images.size();
    }
 
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.pager_image_item, view, false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        loadImage(imageView, images.get(position));
        view.addView(imageLayout, 0);
        return imageLayout;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
 
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
 
    @Override
    public Parcelable saveState() {
        return null;
    }

    private void loadImage(ImageView image, ImageModel.HitsBean image1) {
        Picasso.get()
                .load(image1.getPreviewURL())
                .placeholder(R.mipmap.ic_launcher)
                .into(image);
    }


}