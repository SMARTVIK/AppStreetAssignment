package com.hunter.appstreetassignment.simplegallerycaching;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hunter.appstreetassignment.R;
import com.hunter.appstreetassignment.database.Image;

public class ImageFragment extends Fragment {

  private static final String KEY_IMAGE_RES = "com.google.samples.gridtopager.key.imageRes";

    public static ImageFragment newInstance(Image drawableRes) {
        ImageFragment fragment = new ImageFragment();
        Bundle argument = new Bundle();
        argument.putString(KEY_IMAGE_RES, drawableRes.getImagePath());
        fragment.setArguments(argument);
        return fragment;
    }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      final View view = inflater.inflate(R.layout.pager_image_item, container, false);
      Bundle arguments = getArguments();
      final String imageRes = arguments.getString(KEY_IMAGE_RES);
      view.findViewById(R.id.image).setTransitionName(String.valueOf(imageRes));
      // Load the image with Glide to prevent OOM error when the image drawables are very large.
      Glide.with(getContext())
              .load(imageRes).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
              .listener(new RequestListener<Drawable>() {
                  @Override
                  public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                      L.d(imageRes);
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
