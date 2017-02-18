package com.sanchez.firstphotogallery.common.model.photos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 18.02.2017.
 */

public class Likes implements Parcelable {

    @SerializedName("user_likes")
    public int userLikes;

    @SerializedName("count")
    public long count;

    public Likes() {
    }

    protected Likes(Parcel in) {
        userLikes = in.readInt();
        count = in.readLong();
    }

    public static final Creator<Likes> CREATOR = new Creator<Likes>() {
        @Override
        public Likes createFromParcel(Parcel in) {
            return new Likes(in);
        }

        @Override
        public Likes[] newArray(int size) {
            return new Likes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(userLikes);
        parcel.writeLong(count);
    }
}
