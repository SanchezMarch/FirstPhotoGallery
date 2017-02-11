package com.sanchez.firstphotogallery.common.model.photos;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Олександр on 14.01.2017.
 */

public class Photo implements Serializable { // extends RealmObject {
    @SerializedName("id")
    private long id;

    @SerializedName("album_id")
    private long albumId;

    @SerializedName("owner_id")
    private long ownerId;

    @SerializedName("photo_75")
    private String photoSmall;

    @SerializedName("photo_130")
    private String photoMedium;

    @SerializedName("photo_604")
    private String photoLarge;

    @SerializedName("photo_807")
    private String photoExtraLarge;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private int date;

    @SerializedName("post_id")
    private int postId;

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("reposts")
    private Reposts reposts;

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
        return photoSmall;
    }

    public String getPhoto130() {
        return photoMedium;
    }

    public String getPhoto604() {
        return photoLarge;
    }

    public String getPhoto807() {
        return photoExtraLarge;
    }

    public String getLargestPhoto() {
        if (photoExtraLarge != null) return photoExtraLarge;
        if (photoLarge != null) return photoLarge;
        if (photoMedium != null) return photoMedium;
        return photoSmall;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return new Date(date);
    }

    public int getPostId() {
        return postId;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }





    public static class Likes {

        @SerializedName("user_likes")
        public int userLikes;

        @SerializedName("count")
        public int count;

        public int getUserLikes() {
            return userLikes;
        }

        public int getCount() {
            return count;
        }
    }

    public static class Reposts {

        @SerializedName("count")
        private int count;

        public int getCount() {
            return count;
        }
    }
}
