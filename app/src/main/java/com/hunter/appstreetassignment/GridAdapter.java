package com.hunter.appstreetassignment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sql.DataSource;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageViewHolder> {

    private ArrayList<Image.ImagesBean> data = new ArrayList<>();

    public void setData(ArrayList<Image.ImagesBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
   * A listener that is attached to all ViewHolders to handle image loading events and clicks.
   */
  private interface ViewHolderListener {

    void onLoadCompleted(ImageView view, int adapterPosition);

    void onItemClicked(View view, int adapterPosition);
  }

  private final RequestManager requestManager;
  private final ViewHolderListener viewHolderListener;
  /**
   * Constructs a new grid adapter for the given {@link Fragment}.
   */
  public GridAdapter(Fragment fragment) {
    this.requestManager = Glide.with(fragment);
    this.viewHolderListener = new ViewHolderListenerImpl(fragment);
  }

  @Override
  public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
    return new ImageViewHolder(view, requestManager, viewHolderListener);
  }

  @Override
  public void onBindViewHolder(ImageViewHolder holder, int position) {
    holder.onBind();
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  /**
   * Default {@link ViewHolderListener} implementation.
   */
  private class ViewHolderListenerImpl implements ViewHolderListener {

    private Fragment fragment;
    private AtomicBoolean enterTransitionStarted;

    ViewHolderListenerImpl(Fragment fragment) {
      this.fragment = fragment;
      this.enterTransitionStarted = new AtomicBoolean();
    }

    @Override
    public void onLoadCompleted(ImageView view, int position) {
      // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
      if (MainActivity.currentPosition != position) {
        return;
      }
      if (enterTransitionStarted.getAndSet(true)) {
        return;
      }
      fragment.startPostponedEnterTransition();
    }

    @Override
    public void onItemClicked(View view, int position) {
        MainActivity.currentPosition = position;
        ((TransitionSet) fragment.getExitTransition()).excludeTarget(view, true);
        ImageView transitioningView = view.findViewById(R.id.image);

        ImagePagerFragment card_image = new ImagePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", data);
        card_image.setArguments(bundle);

        fragment.getFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true) // Optimize for shared element transition
                .addSharedElement(transitioningView, transitioningView.getTransitionName())
                .replace(R.id.fragment_container, card_image, ImagePagerFragment.class
                        .getSimpleName())
                .addToBackStack(null)
                .commit();
    }
  }

  /**
   * ViewHolder for the grid's images.
   */
  class ImageViewHolder extends RecyclerView.ViewHolder implements
      View.OnClickListener {

    private final ImageView image;
    private final RequestManager requestManager;
    private final ViewHolderListener viewHolderListener;

      ImageViewHolder(View itemView, RequestManager requestManager,
                      ViewHolderListener viewHolderListener) {
          super(itemView);
          this.image = itemView.findViewById(R.id.image);
          this.requestManager = requestManager;
          this.viewHolderListener = viewHolderListener;
          itemView.findViewById(R.id.card_view).setOnClickListener(this);
      }

    /**
     * Binds this view holder to the given adapter position.
     *
     * The binding will load the image into the image view, as well as set its transition name for
     * later.
     */
    void onBind() {
      int adapterPosition = getAdapterPosition();
      setImage(adapterPosition);
      image.setTransitionName(data.get(adapterPosition).getUrl());
    }

    void setImage(final int adapterPosition) {
      // Load the image with Glide to prevent OOM error when the image drawables are very large.
      requestManager
          .load(data.get(adapterPosition).getUrl())
          .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                        Target<Drawable> target, boolean isFirstResource) {
              viewHolderListener.onLoadCompleted(image, adapterPosition);
              return false;
            }

              @Override
              public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                  viewHolderListener.onLoadCompleted(image, adapterPosition);
                  return false;
              }
          })
          .into(image);
    }

    @Override
    public void onClick(View view) {
      // Let the listener start the ImagePagerFragment.
      viewHolderListener.onItemClicked(view, getAdapterPosition());
    }
  }

}