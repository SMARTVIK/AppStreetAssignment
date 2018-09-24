package com.hunter.appstreetassignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class DetailScreen extends AppCompatActivity {

    private ArrayList<ImageModel.HitsBean> images = new ArrayList<>();

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
