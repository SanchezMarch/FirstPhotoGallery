package com.sanchez.firstphotogallery.common.network.api.services;

import com.sanchez.firstphotogallery.common.model.responses.user.UsersResponse;
import com.sanchez.firstphotogallery.common.model.user.Field;
import com.sanchez.firstphotogallery.common.model.user.NameCase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Олександр on 16.12.2016.
 */

public interface ProfileService {
    @GET("users.get")
    Call<UsersResponse> get(
            @Query("user_ids") long[] ids,
            @Query("fields[]") Field[] fields,
            @Query("name_case") NameCase nameCase
    );
}
