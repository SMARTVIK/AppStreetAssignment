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

package com.hunter.appstreetassignment.simplegallerycaching;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hunter.appstreetassignment.detail.Api;
import com.hunter.appstreetassignment.R;
import com.hunter.appstreetassignment.database.Image;
import com.hunter.appstreetassignment.database.ImageViewModel;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment for displaying a grid of images.
 */
public class GridFragment extends Fragment {
    private RecyclerView recyclerView;
    private GridAdapter gridAdapter;
    private List<Image> mImages = new ArrayList<>();
    private List<Image> mSearchingImages = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private boolean isSearching;
    private String searchQuery = "";
    private ImageViewModel imageViewModel;
    private EndlessRecyclerViewScrollListener endlessListener;
    private int spanCount = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.grid, container, false);
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        gridAdapter = new GridAdapter(this, getContext());
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanCount(spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        endlessListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                L.d("result page number " + page);
                getImagesFromServer(page, isSearching, searchQuery, false);

                if (page == 1) {
                    endlessListener.previousTotalItemCount = 0;
                }
            }
        };

        recyclerView.addOnScrollListener(endlessListener);
        imageViewModel.getAllImages().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(@Nullable List<Image> images) {
                dismissDialog();
                if (isSearching) {
                    List<Image> newImages = new ArrayList<>();
                    for (Image image : images) {
                        if (image.getTag().startsWith(searchQuery)) {
                            newImages.add(image);
                        }
                    }
                    mSearchingImages = newImages;
                    gridAdapter.setLoadedData(mSearchingImages);
                } else {
                    List<Image> newImages = new ArrayList<>();
                    for (Image image : images) {
                        if (image.getTag().length() == 0) {
                            newImages.add(image);
                        }
                    }
                    mImages = newImages;
                    gridAdapter.setLoadedData(mImages);
                }
            }
        });

        prepareTransitions();
        postponeEnterTransition();
        if (Utility.isNetworkConnected(getContext())) {
            getImagesFromServer(1, isSearching, searchQuery, mImages.size()>0?false:true);
        }
        return recyclerView;
    }

    private Api getApi() {
        Gson gson = new Gson();
        Api api = new Retrofit.Builder().baseUrl(Constants.PIXA_BAY).addConverterFactory(GsonConverterFactory.create(gson)).build().create(Api.class);
        return api;
    }

    private ProgressDialog progressDialog = null;

    private void getImagesFromServer(final int page, final boolean isSearching, String query, boolean main) {
        if (!Utility.isNetworkConnected(getContext())) {
            return;
        }

        if(main){
            progressDialog = ProgressDialog.show(getContext(),"Loading...","");
        }else{
            gridAdapter.setShowProgress(true);
        }
        L.d("getting images from server");
        Constants.getPhotos(isSearching ? query : "", getContext(), page, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                L.d("onSuccess " + page);
                String string = new String(responseBody);
                ImageModel image = ImageModel.objectFromData(string);
                storeAndDownloadImages(image);
                dismissDialog();
                gridAdapter.setShowProgress(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                L.d("result onFailure " + error.getMessage());
                dismissDialog();
                gridAdapter.setShowProgress(false);
            }
        });
    }

    private void dismissDialog() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    private void storeAndDownloadImages(ImageModel image) {
        for (ImageModel.HitsBean hitsBean : image.getHits()) {
            new DownloadFile(hitsBean).execute();
        }
    }

    class DownloadFile extends AsyncTask<String,Long,String> {

        private final ImageModel.HitsBean imageModel;
        private String urlString;

        public DownloadFile(ImageModel.HitsBean imageModel){
            this.imageModel = imageModel;
            urlString = imageModel.getPreviewURL();
        }

        ProgressDialog mProgressDialog = new ProgressDialog(getContext());// Change Mainactivity.this with your activity name.
        String strFolderName="appStreet";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
  /*          mProgressDialog.setMessage("Downloading");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.show();
  */      }

        @Override
        protected String doInBackground(String... aurl) {

            String filePath = null;
            /*
            int count;
            try {
                URL url = new URL(urlString);
                URLConnection conexion = url.openConnection();
                conexion.connect();
                String targetFileName=urlString.substring(urlString.lastIndexOf("/")+1);//Change name and subname
                int lenghtOfFile = conexion.getContentLength();
                File SDCardRoot = new File(Environment.getExternalStorageDirectory(),"abcd");
                if(!SDCardRoot.exists()){
                    SDCardRoot.mkdir();
                }
                //create a new file, specifying the path, and the filename which we want to save the file as.
                File file = new File(SDCardRoot,targetFileName);
                InputStream input = new BufferedInputStream(url.openStream());
                filePath = file.getAbsolutePath();
                OutputStream output = new FileOutputStream(filePath);
                byte data[] = new byte[1024];
                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    long integer = (total * 100 / lenghtOfFile);
                    publishProgress(integer);
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
                return filePath;
            } catch (Exception e) {
                L.d(e.getStackTrace());
            }
*/
            return filePath;
        }

        protected void onProgressUpdate(Long... progress) {
    /*        mProgressDialog.setProgress(progress[0].intValue());
            if(mProgressDialog.getProgress()==mProgressDialog.getMax()){
                mProgressDialog.dismiss();
                Toast.makeText(getContext(), "File Downloaded", Toast.LENGTH_SHORT).show();
            }
    */    }

        @Override
        protected void onPostExecute(String result) {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            Image image = new Image(String.valueOf(imageModel.getId()), imageModel.getPreviewURL(), false,searchQuery);
            imageViewModel.insert(image);
        }
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
                final GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) {
                    return;
                }
                View viewAtPosition = layoutManager.findViewByPosition(MainActivity.currentPosition);
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
        setExitTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.grid_exit_transition));
        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        // Locate the ViewHolder for the clicked position.
                        RecyclerView.ViewHolder selectedViewHolder = recyclerView.findViewHolderForAdapterPosition(MainActivity.currentPosition);
                        if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
                            L.d("result sww returning");
                            return;
                        }

                        L.d("everything seems right");
                        sharedElements.put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.image));
                    }
                });
    }

    public void setSpanCount(int count) {
        spanCount = count;
        gridLayoutManager.setSpanCount(spanCount);
    }

    public void isSearching(boolean isSearching,String query) {
        mSearchingImages.clear();
        searchQuery = query;
        this.isSearching = isSearching;
        if (!isSearching) {
            gridAdapter.setLoadedData(mImages);
        } else {
            if (isSearching && query.length() == 0) {
                LiveData<List<Image>> liveImage = imageViewModel.getAllImages();
                List<Image> images = liveImage.getValue();
                List<Image> newImages = new ArrayList<>();
                for (Image image : images) {
                    if (image.getTag().length() == 0) {
                        newImages.add(image);
                    }
                }
                mImages = newImages;
                gridAdapter.setLoadedData(mImages);
            }
        }

        if (isSearching && query.length() > 0) {
            if (Utility.isNetworkConnected(getContext())) {
                getImagesFromServer(1, isSearching, query, false);
            } else {
                LiveData<List<Image>> liveImage = imageViewModel.getAllImages();
                List<Image> images = liveImage.getValue();
                List<Image> newImages = new ArrayList<>();
                for (Image image : images) {
                    if (image.getTag().startsWith(searchQuery)) {
                        newImages.add(image);
                    }
                }
                mSearchingImages = newImages;
                gridAdapter.setLoadedData(mSearchingImages);
            }
        }
    }
}
