package com.hunter.appstreetassignment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.sql.DataSource;

public class ImageFragment extends Fragment {

  private static final String KEY_IMAGE_RES = "com.google.samples.gridtopager.key.imageRes";

  public static ImageFragment newInstance(Image.ImagesBean drawableRes) {
    ImageFragment fragment = new ImageFragment();
    Bundle argument = new Bundle();
    argument.putString(KEY_IMAGE_RES, drawableRes.getUrl());
    fragment.setArguments(argument);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.pager_image_item, container, false);
    Bundle arguments = getArguments();
    String imageRes = arguments.getString(KEY_IMAGE_RES);
    view.findViewById(R.id.image).setTransitionName(String.valueOf(imageRes));
    // Load the image with Glide to prevent OOM error when the image drawables are very large.
      Glide.with(this)
              .load(imageRes)
              .listener(new RequestListener<Drawable>() {
                  @Override
                  public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                      getParentFragment().startPostponedEnterTransition();
                      return false;
                  }

                  @Override
                  public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                      getParentFragment().startPostponedEnterTransition();
                      return false;
                  }
              })
              .into((ImageView) view.findViewById(R.id.image));
    return view;
  }
}
