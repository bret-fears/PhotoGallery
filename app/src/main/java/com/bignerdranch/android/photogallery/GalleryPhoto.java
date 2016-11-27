package com.bignerdranch.android.photogallery;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bretfears on 11/27/16.
 */

public class GalleryPhoto {

    @SerializedName("photo")
    private List<GalleryItem> galleryItems;
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;

    public GalleryPhoto() {
    }


    public List<GalleryItem> getGalleryItems() {
        return galleryItems;
    }

    public void setGalleryItems(List<GalleryItem> galleryItems) {
        this.galleryItems = galleryItems;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
