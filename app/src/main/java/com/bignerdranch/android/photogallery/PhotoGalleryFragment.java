package com.bignerdranch.android.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bretfears on 11/22/16.
 */

public class PhotoGalleryFragment extends Fragment {

    private static final String TAG = "PhotoGalleryFragment";

    private RecyclerView mPhotoRecyclerView;
    private PhotoAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<GalleryItem> mItems = new ArrayList<>();
    private boolean mLoading = true;
    private int mPage = 1;
    int mPastVisibleItems, mVisibleItemCount, mTotalItemCount;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute(mPage);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mPhotoRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_photo_gallery_recycler_view);
        mGridLayoutManager = new GridLayoutManager(getActivity(), /*spanCount=*/3);
        mPhotoRecyclerView.setLayoutManager(mGridLayoutManager);

        mPhotoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    mVisibleItemCount = mGridLayoutManager.getChildCount();
                    mTotalItemCount = mGridLayoutManager.getItemCount();
                    mPastVisibleItems = mGridLayoutManager.findFirstVisibleItemPosition();

                    if (mLoading) {
                        if ((mVisibleItemCount + mPastVisibleItems) >= mTotalItemCount) {
                            mLoading = false;
                            mPage++;
                            new FetchItemsTask().execute(mPage);
                        }
                    }
                }
            }
        });

        setupAdapter();

        return v;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mAdapter = new PhotoAdapter((mItems));
            mPhotoRecyclerView.setAdapter(mAdapter);
        }
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;

        public PhotoHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView;
        }

        public void bindGalleryItem(GalleryItem item) {
            mTitleTextView.setText(item.toString());
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<GalleryItem> mGalleryItemList;

        public PhotoAdapter(List<GalleryItem> galleryItemList) {
            mGalleryItemList = galleryItemList;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getActivity());
            return new PhotoHolder(textView);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            GalleryItem galleryItem = mGalleryItemList.get(position);
            holder.bindGalleryItem(galleryItem);
        }

        @Override
        public int getItemCount() {
            return mGalleryItemList.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Integer, Void, List<GalleryItem>> {

        @Override
        protected List<GalleryItem> doInBackground(Integer... page) {
            return new FlickrFetchr().fetchItems(page[0]);
        }

        @Override
        protected void onPostExecute(List<GalleryItem> galleryItems) {
            mLoading = true;
            if (mItems.size() <= 0) {
                mItems = galleryItems;
                setupAdapter();
            } else {
                mItems.addAll(galleryItems);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
