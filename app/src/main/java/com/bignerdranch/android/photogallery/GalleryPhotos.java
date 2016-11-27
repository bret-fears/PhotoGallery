package com.bignerdranch.android.photogallery;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bretfears on 11/27/16.
 */

public class GalleryPhotos {

    @SerializedName("photos")
    private GalleryPhoto photo;

    public GalleryPhotos() {
    }

    public GalleryPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(GalleryPhoto photo) {
        this.photo = photo;
    }
}
