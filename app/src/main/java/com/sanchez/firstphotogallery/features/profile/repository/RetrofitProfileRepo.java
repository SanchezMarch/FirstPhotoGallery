package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.model.responses.user.UsersResponse;
import com.sanchez.firstphotogallery.common.model.user.Field;
import com.sanchez.firstphotogallery.common.model.user.NameCase;
import com.sanchez.firstphotogallery.common.model.user.User;
import com.sanchez.firstphotogallery.common.network.api.client.VkClient;
import com.sanchez.firstphotogallery.common.network.api.services.ProfileService;
import com.sanchez.firstphotogallery.common.repo.Repo;
import com.sanchez.firstphotogallery.common.repo.RetrofitRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Олександр on 13.01.2017.
 */

public class RetrofitProfileRepo extends Repo
        implements IProfileRepo {

    private ProfileService profileService;

    public RetrofitProfileRepo() {
        this.profileService = VkClient.makeService(ProfileService.class);
    }

    @Override
    public void getProfile(long id, final Result<User> onSuccess, final Result<VkError> onError) {
        profileService.get(
                new long[]{id},
                new Field[]{
                        Field.PHOTO_MAX_ORIG,
                        Field.STATUS,
                        Field.COUNTERS,
                },
                NameCase.NOMINATIVE)
                .enqueue(new RetrofitRepo.VkCallback<>(new Result<UsersResponse>() {
                    @Override
                    public void response(UsersResponse usersResponse) {
                        onSuccess.response(usersResponse.getFirst());
                    }
                }, onError));
    }
}
