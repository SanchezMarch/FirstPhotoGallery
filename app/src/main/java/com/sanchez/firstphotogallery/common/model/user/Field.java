package com.sanchez.firstphotogallery.common.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 13.01.2017.
 */

public enum Field {

    @SerializedName("photo_id")
    PHOTO_ID,

    @SerializedName("verified")
    VERIFIED,

    @SerializedName("blacklisted")
    BLACKLISTED,


    @SerializedName("sex")
    SEX,

    @SerializedName("bdate")
    BDATE,


    @SerializedName("city")
    CITY,

    @SerializedName("country")
    COUNTRY,

    @SerializedName("home_town")
    HOME_TOWN,


    @SerializedName("photo_50")
    PHOTO_50,

    @SerializedName("photo_100")
    PHOTO_100,

    @SerializedName("photo_200")
    PHOTO_200,

    @SerializedName("photo_200_orig")
    PHOTO_200_ORIG,

    @SerializedName("photo_400_orig")
    PHOTO_400_ORIG,

    @SerializedName("photo_max")
    PHOTO_MAX,

    @SerializedName("photo_max_orig")
    PHOTO_MAX_ORIG,


    @SerializedName("online")
    ONLINE,

    @SerializedName("domain")
    DOMAIN,


    @SerializedName("has_mobile")
    HAS_MOBILE,

    @SerializedName("contacts")
    CONTACTS,

    @SerializedName("site")
    SITE,


    @SerializedName("education")
    EDUCATION,

    @SerializedName("universities")
    UNIVERSITIES,

    @SerializedName("schools")
    SCHOOLS,


    @SerializedName("status")
    STATUS,

    @SerializedName("last_seen")
    LAST_SEEN,


    @SerializedName("followers_count")
    FOLLOWERS_COUNT,

    @SerializedName("common_count")
    COMMON_COUNT,

    @SerializedName("counters")
    COUNTERS,


    @SerializedName("occupation")
    OCCUPATION,

    @SerializedName("relatives")
    RELATIVES,

    @SerializedName("relation")
    RELATION,


    @SerializedName("personal")
    PERSONAL,


    @SerializedName("connections")
    CONNECTIONS,

    @SerializedName("exports")
    EXPORTS,


    @SerializedName("wall_comments")
    WALL_COMMENTS,


    @SerializedName("activities")
    ACTIVITIES,

    @SerializedName("interests")
    INTERESTS,

    @SerializedName("music")
    MUSIC,

    @SerializedName("movies")
    MOVIES,

    @SerializedName("tv")
    TV,

    @SerializedName("books")
    BOOKS,

    @SerializedName("games")
    GAMES,

    @SerializedName("about")
    ABOUT,

    @SerializedName("quotes")
    QUOTES,


    @SerializedName("can_post")
    CAN_POST,

    @SerializedName("can_see_all_posts")
    CAN_SEE_ALL_POSTS,

    @SerializedName("can_see_audio")
    CAN_SEE_AUDIO,

    @SerializedName("can_write_private_message")
    CAN_WRITE_PRIVATE_MESSAGE,

    @SerializedName("can_send_friend_request")
    CAN_SEND_FRIEND_REQUEST,


    @SerializedName("is_favorite")
    IS_FAVORITE,

    @SerializedName("timezone")
    TIMEZONE,


    @SerializedName("screen_name")
    SCREEN_NAME,

    @SerializedName("maiden_name")
    MAIDEN_NAME,


    @SerializedName("crop_photo")
    CROP_PHOTO,

    @SerializedName("friend_status")
    FRIEND_STATUS
}
