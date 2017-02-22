package com.sanchez.firstphotogallery.common.network.api.services;

import com.sanchez.firstphotogallery.common.model.albums.AlbumResponse;
import com.sanchez.firstphotogallery.common.model.responses.photos.AllPhotosResponse;
import com.sanchez.firstphotogallery.common.model.responses.photos.PhotosGeoResponse;
import com.sanchez.firstphotogallery.common.network.api.utils.BooleanInt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Олександр on 14.01.2017.
 */

public interface PhotosService {

    @GET("photos.getAll")
    Call<AllPhotosResponse> getAll(
            @Query("owner_id") long ownerId,
            @Query("extended") BooleanInt extended,
            @Query("need_hidden") BooleanInt needHidden,
            @Query("offset") int offset,
            @Query("count") int count
    );

    @GET("photos.getAlbums")
    Call<AlbumResponse> getAlbums(
            @Query("owner_id") long user_ids,
            @Query("need_system") BooleanInt needSystem,
            @Query("need_covers") BooleanInt needCovers,
            @Query("offset") int offset,
            @Query("count") int count
    );

    @GET("photos.search")
    Call<PhotosGeoResponse> getPhotosMapSearch(
        @Query("lat") double latitude,
        @Query("long") double longitude,
        @Query("offset") int offset,
        @Query("count") int count,
        @Query("radius") int radius
    );
}

