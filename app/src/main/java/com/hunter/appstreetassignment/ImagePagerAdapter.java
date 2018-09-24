package com.hunter.appstreetassignment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hunter.appstreetassignment.database.Image;

import java.util.ArrayList;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

  ArrayList<Image> imagesBeans = new ArrayList<>();

  public ImagePagerAdapter(Fragment fragment) {
    super(fragment.getChildFragmentManager());
  }

  public void setData(ArrayList<Image> imagesBeans) {
    this.imagesBeans = (ArrayList<Image>) imagesBeans.clone();
//    L.d("result images size "+imagesBeans.size() + "");
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return imagesBeans.size();
  }

  @Override
  public int getItemPosition(@NonNull Object object) {
    return POSITION_NONE;
  }

  @Override
  public Fragment getItem(int position) {
    return ImageFragment.newInstance(imagesBeans.get(position));
  }
}
