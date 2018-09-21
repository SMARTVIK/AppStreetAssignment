package com.hunter.appstreetassignment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailScreen extends AppCompatActivity {

    private ArrayList<Image.ImagesBean> images = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        images = getIntent().getParcelableArrayListExtra("list");
        initViewPager();
    }

    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        SlidingImage_Adapter imagePagerAdapter = new SlidingImage_Adapter(this,images);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("pos",0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }
}
