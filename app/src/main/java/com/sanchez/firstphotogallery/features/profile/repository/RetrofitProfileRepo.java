package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.responses.user.UsersResponse;
import com.sanchez.firstphotogallery.common.model.user.Field;
import com.sanchez.firstphotogallery.common.model.user.NameCase;
import com.sanchez.firstphotogallery.common.model.user.User;
import com.sanchez.firstphotogallery.common.network.api.client.VkClient;
import com.sanchez.firstphotogallery.common.network.api.services.ProfileService;
import com.sanchez.firstphotogallery.common.repo.Repo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Олександр on 13.01.2017.
 */

public class RetrofitProfileRepo extends Repo
        implements IProfileRepo {

    private ProfileService profileService;

    public RetrofitProfileRepo(){
        this.profileService = VkClient.makeService(ProfileService.class);
    }

    @Override
    public void getProfile(long id, final Result<User> onSuccess, final Result<Throwable> onError) {
        profileService.get(
                new long[]{id},
                new Field[]{
                        Field.PHOTO_MAX_ORIG,
                        Field.STATUS,
                        Field.COUNTERS,
                },
                NameCase.NOMINATIVE)
                .enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        onSuccess.response(response.body().getFirst());
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        onError.response(t);
                    }
                });
    }
}
