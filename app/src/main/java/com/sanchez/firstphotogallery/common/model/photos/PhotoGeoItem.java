package com.sanchez.firstphotogallery.common.model.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 19.02.2017.
 */

public class PhotoGeoItem {

    @SerializedName("id")
    private long id;

    @SerializedName("album_id")
    private long albumId;

    @SerializedName("owner_id")
    private long ownerId;

    @SerializedName("photo_75")
    private String photo75;

    @SerializedName("photo_130")
    private String photo130;

    @SerializedName("photo_604")
    private String photo604;

    @SerializedName("photo_807")
    private String photo807;

    @SerializedName("photo_1280")
    private String photo1280;

    @SerializedName("width")
    private long width;

    @SerializedName("height")
    private long height;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private long date;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("long")
    private double longitude;

    @SerializedName("post_id")
    private long postId;

    @SerializedName("photo_2560")
    private String photo2560;

    @SerializedName("user_id")
    private long userId;

    public long getId() {
        return id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getPhoto75() {
        return photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public String getPhoto1280() {
        return photo1280;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public long getDate() {
        return date;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getPostId() {
        return postId;
    }

    public String getPhoto2560() {
        return photo2560;
    }

    public long getUserId() {
        return userId;
    }
}
