package com.sanchez.firstphotogallery.common.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 13.01.2017.
 */

public class User {

    @SerializedName("id")
    private long id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("photo_max_orig")
    private String photo;

    @SerializedName("status")
    private String status;

    @SerializedName("counters")
    private Counters counters;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getStatus() {
        return status;
    }

    public Counters getCounters() {
        return counters;
    }

    public static class Counters {

        @SerializedName("albums")
        private int albums;

        @SerializedName("videos")
        private int videos;

        @SerializedName("audios")
        private int audios;

        @SerializedName("notes")
        private int notes;

        @SerializedName("photos")
        private int photos;

        @SerializedName("groups")
        private int groups;

        @SerializedName("gifts")
        private int gifts;

        @SerializedName("friends")
        private int friends;

        @SerializedName("online_friends")
        private int onlineFriends;

        @SerializedName("mutual_friends")
        private int mutualFriends;

        @SerializedName("user_photos")
        private int userPhotos;

        @SerializedName("user_videos")
        private int userVideos;

        @SerializedName("followers")
        private int followers;

        @SerializedName("subscriptions")
        private int subscriptions;

        @SerializedName("pages")
        private int pages;

        public int getAlbums() {
            return albums;
        }

        public int getVideos() {
            return videos;
        }

        public int getAudios() {
            return audios;
        }

        public int getNotes() {
            return notes;
        }

        public int getPhotos() {
            return photos;
        }

        public int getGroups() {
            return groups;
        }

        public int getGifts() {
            return gifts;
        }

        public int getFriends() {
            return friends;
        }

        public int getOnlineFriends() {
            return onlineFriends;
        }

        public int getMutualFriends() {
            return mutualFriends;
        }

        public int getUserPhotos() {
            return userPhotos;
        }

        public int getUserVideos() {
            return userVideos;
        }

        public int getFollowers() {
            return followers;
        }

        public int getSubscriptions() {
            return subscriptions;
        }

        public int getPages() {
            return pages;
        }
    }
}
