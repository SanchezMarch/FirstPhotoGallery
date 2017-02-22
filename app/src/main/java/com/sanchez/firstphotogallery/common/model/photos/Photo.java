package com.sanchez.firstphotogallery.common.model.photos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Comment;

import java.util.Date;


/**
 * Created by Олександр on 14.01.2017.
 */

public class Photo implements Parcelable { // extends RealmObject {
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
    private long width;

    @SerializedName("height")
    private long height;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private long date;

    @SerializedName("post_id")
    private long postId;

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("reposts")
    private Reposts reposts;

    @SerializedName("comments")
    private Comments comments;

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

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return new Date(date);
    }

    public long getPostId() {
        return postId;
    }

    public Likes getLikes() {
        return likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public Comments getComments() {
        return comments;
    }


    public Photo() {
    }

    protected Photo(Parcel in) {
        id = in.readLong();
        albumId = in.readLong();
        ownerId = in.readLong();
        photoSmall = in.readString();
        photoMedium = in.readString();
        photoLarge = in.readString();
        photoExtraLarge = in.readString();
        width = in.readLong();
        height = in.readLong();
        text = in.readString();
        date = in.readLong();
        postId = in.readLong();
        likes = in.readParcelable(getClass().getClassLoader());
        reposts = in.readParcelable(getClass().getClassLoader());
        comments = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeLong(albumId);
        parcel.writeLong(ownerId);
        parcel.writeString(photoSmall);
        parcel.writeString(photoMedium);
        parcel.writeString(photoLarge);
        parcel.writeString(photoExtraLarge);
        parcel.writeLong(width);
        parcel.writeLong(height);
        parcel.writeString(text);
        parcel.writeLong(date);
        parcel.writeLong(postId);
        parcel.writeParcelable(likes, flags);
        parcel.writeParcelable(reposts, flags);
        parcel.writeParcelable(comments, flags);
    }


}
