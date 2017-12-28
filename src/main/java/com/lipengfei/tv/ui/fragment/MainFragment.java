/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lipengfei.tv.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lipengfei.tv.R;
import com.lipengfei.tv.bean.Channel;
import com.lipengfei.tv.bean.Movie;
import com.lipengfei.tv.bean.MovieCategoryList;
import com.lipengfei.tv.global.Variables;
import com.lipengfei.tv.manager.MovieManager;
import com.lipengfei.tv.presenter.CardPresenter;
import com.lipengfei.tv.ui.activity.BrowseErrorActivity;
import com.lipengfei.tv.ui.activity.DetailsActivity;
import com.lipengfei.tv.ui.activity.PlayerActivity;
import com.lipengfei.tv.ui.activity.SearchActivity;
import com.lipengfei.tv.ui.activity.SettingActivity;

import java.net.URI;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainFragment extends BrowseFragment {
    private static final String TAG = "MainFragment";

    private static final int BACKGROUND_UPDATE_DELAY = 300;


    private final Handler mHandler = new Handler();
    private ArrayObjectAdapter mRowsAdapter;
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private URI mBackgroundURI;
    private BackgroundManager mBackgroundManager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);

        prepareBackgroundManager();

        setupUIElements();

        loadRows();

        setupEventListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
        startBackgroundTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mBackgroundTimer) {
            Log.d(TAG, "onDestroy: " + mBackgroundTimer.toString());
            mBackgroundTimer.cancel();
        }
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        MovieManager.cacheLive(new MovieManager.CacheChannelListener() {
            @Override
            public void onSuccess(LinkedList<Channel> channels) {
                ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());
                for(Channel channel : channels){
                    listRowAdapter.add(channel);
                }
                HeaderItem header = new HeaderItem(100, "Live");
                mRowsAdapter.add(new ListRow(header, listRowAdapter));
            }

            @Override
            public void onFailed(String msg) {

            }
        });

        MovieManager.cacheMovie(new MovieManager.CacheMovieListener() {
            @Override
            public void onSuccess(SparseArray<MovieCategoryList> movieLists) {
                CardPresenter cardPresenter = new CardPresenter();
                for(int i = 0;i<movieLists.size();i++){
                    int key =  movieLists.keyAt(i);
                    MovieCategoryList bean = movieLists.get(key);
                    ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);
                    for(Movie movie : bean.movies){
                        listRowAdapter.add(movie);
                    }
                    HeaderItem header = new HeaderItem(key, bean.getCategoryNameByLanguageCode(Variables.language_code));
                    mRowsAdapter.add(new ListRow(header, listRowAdapter));
                }

//                HeaderItem setheader = new HeaderItem(120,"设置");
//                GridItemPresenter gridItemPresenter = new GridItemPresenter(getActivity());
//                ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(gridItemPresenter);
//                gridRowAdapter.add("Setting");
////                gridRowAdapter.add(getString(R.string.error_fragment));
//                mRowsAdapter.add(new ListRow(setheader, gridRowAdapter));

                setAdapter(mRowsAdapter);
            }

            @Override
            public void onFailed(String msg) {

            }
        });

    }

    private void prepareBackgroundManager() {

        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mDefaultBackground = getResources().getDrawable(R.drawable.default_background);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(
        // R.drawable.videos_by_google_banner));
        setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.fastlane_background));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void setupEventListeners() {
        setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    protected void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(getActivity())
                .load(uri)
                .centerCrop()
                .error(mDefaultBackground)
                .bitmapTransform(new BlurTransformation(getActivity(),23,4))
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable>
                                                        glideAnimation) {
                        mBackgroundManager.setDrawable(resource);
                    }
                });
        mBackgroundTimer.cancel();
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Movie) {
                Movie movie = (Movie) item;
                Log.d(TAG, "Item: " + item.toString());
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.MOVIE, movie);
                intent.putExtra(DetailsActivity.TAGKEY,(int)row.getHeaderItem().getId());

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
                        DetailsActivity.SHARED_ELEMENT_NAME).toBundle();
                getActivity().startActivity(intent, bundle);
            } else if (item instanceof String) {
                if (((String) item).indexOf(getString(R.string.error_fragment)) >= 0) {
                    Intent intent = new Intent(getActivity(), BrowseErrorActivity.class);
                    startActivity(intent);
                }else if (((String) item).indexOf("Setting") >= 0) {
                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_SHORT)
                            .show();
                }
            }else if(item instanceof Channel){
                Channel channel = (Channel) item;
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("position",MovieManager.channelList.indexOf(channel));
                startActivity(intent);
                Log.d(TAG, "Item: " + item.toString());
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Movie) {
                mBackgroundURI = URI.create(((Movie) item).getBackgroundUrl());
                startBackgroundTimer();
            }

        }
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mBackgroundURI != null) {
                        updateBackground(mBackgroundURI.toString());
                    }
                }
            });

        }
    }

}
