/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hunter.appstreetassignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment for displaying a grid of images.
 */
public class GridFragment extends Fragment {

  private RecyclerView recyclerView;
  private GridAdapter gridAdapter;
  private ArrayList<Image.ImagesBean> mImages = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    recyclerView = (RecyclerView) inflater.inflate(R.layout.grid, container, false);
    gridAdapter = new GridAdapter(this);
    recyclerView.setAdapter(gridAdapter);
    prepareTransitions();
    postponeEnterTransition();
    getImagesFromServer();
    return recyclerView;
  }

  private Api getApi() {
    Gson gson = new Gson();
    Api api = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(Api.class);
    return api;
  }

  private void getImagesFromServer() {
    Call<Image> call = getApi().getPhotos();
    call.enqueue(new Callback<Image>() {

      @Override
      public void onResponse(Call<Image> call, Response<Image> response) {
        Log.d("result","");
        mImages = response.body().getImages();
        gridAdapter.setData(mImages);
      }

      @Override
      public void onFailure(Call<Image> call, Throwable t) {
        Log.d("result","");
      }
    });
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    scrollToPosition();
  }

  private void scrollToPosition() {
    recyclerView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View v,
          int left,
          int top,
          int right,
          int bottom,
          int oldLeft,
          int oldTop,
          int oldRight,
          int oldBottom) {
        recyclerView.removeOnLayoutChangeListener(this);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        View viewAtPosition = layoutManager.findViewByPosition(MainActivity.currentPosition);
        // Scroll to position if the view for the current position is null (not currently part of
        // layout manager children), or it's not completely visible.
        if (viewAtPosition == null || layoutManager
            .isViewPartiallyVisible(viewAtPosition, false, true)) {
          recyclerView.post(new Runnable() {
            @Override
            public void run() {
              layoutManager.scrollToPosition(MainActivity.currentPosition);
            }
          });
        }
      }
    });
  }

  private void prepareTransitions() {
    setExitTransition(TransitionInflater.from(getContext())
            .inflateTransition(R.transition.grid_exit_transition));
    // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
    setExitSharedElementCallback(
            new SharedElementCallback() {
              @Override
              public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                // Locate the ViewHolder for the clicked position.
                RecyclerView.ViewHolder selectedViewHolder = recyclerView.findViewHolderForAdapterPosition(MainActivity.currentPosition);
                if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
                  return;
                }
                sharedElements.put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.image));
              }
            });
  }


}
