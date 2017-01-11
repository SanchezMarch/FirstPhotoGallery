package com.sanchez.firstphotogallery.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 26.12.2016.
 */

public class Photo {

    @SerializedName("thumb")
    private String thumb;

    public String getThumb() {
        return thumb;
    }
}
