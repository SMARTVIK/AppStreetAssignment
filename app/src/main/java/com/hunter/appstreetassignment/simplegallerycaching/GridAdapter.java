package com.hunter.appstreetassignment.simplegallerycaching;

import android.content.Context;
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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.hunter.appstreetassignment.R;
import com.hunter.appstreetassignment.database.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PROGRESS_VIEW = 2;
    private static final int NORMAL_VIEW = 1;

    private ArrayList<ImageModel.HitsBean> data = new ArrayList<>();
    private List<Image> loadedData = new ArrayList<>();
    private Context context;
    private boolean showProgress;


    public void setData(ArrayList<ImageModel.HitsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (showProgress && position == loadedData.size()) {
            return PROGRESS_VIEW;
        } else {
            return NORMAL_VIEW;
        }
    }

    public void setLoadedData(List<Image> loadedData) {
        this.loadedData = loadedData;
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
  public GridAdapter(Fragment fragment, Context context) {
      this.requestManager = Glide.with(fragment);
      this.context = context;
      this.viewHolderListener = new ViewHolderListenerImpl(fragment);
  }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (PROGRESS_VIEW == viewType) {
            viewHolder =  new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_view, parent, false));
        } else {
            viewHolder =  new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false),requestManager, viewHolderListener);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        if (holder1 instanceof ImageViewHolder && position != loadedData.size()) {
            ((ImageViewHolder) holder1).onBind();
        }
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
//        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return showProgress ? loadedData.size() + 1 : loadedData.size();
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
        bundle.putParcelableArrayList("list", getArrayList());
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

    private ArrayList<Image> getArrayList() {
      ArrayList<Image> images = new ArrayList<>();
        for (Image loadedDatum : loadedData) {
            images.add(loadedDatum);
        }
      return images;
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
      image.setTransitionName(loadedData.get(adapterPosition).getImagePath());
//      image.setTransitionName(data.get(adapterPosition).getPreviewURL());
    }

    void setImage(final int adapterPosition) {
      // Load the image with Glide to prevent OOM error when the image drawables are very large.
//        File file = new File(loadedData.get(adapterPosition).getImagePath());
        String stringUrl = loadedData.get(adapterPosition).getImagePath();
        Glide.with(context)
                .load(stringUrl).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        viewHolderListener.onLoadCompleted(image, adapterPosition);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
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

    class ProgressViewHolder extends RecyclerView.ViewHolder{

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

}