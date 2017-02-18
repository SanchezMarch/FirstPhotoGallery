package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.photos.Photo;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.model.responses.photos.AllPhotosResponse;
import com.sanchez.firstphotogallery.common.network.api.client.VkClient;
import com.sanchez.firstphotogallery.common.network.api.services.PhotosService;
import com.sanchez.firstphotogallery.common.network.api.utils.BooleanInt;
import com.sanchez.firstphotogallery.common.repo.Repo;
import com.sanchez.firstphotogallery.common.repo.RetrofitRepo;

import java.util.ArrayList;

/**
 * Created by Олександр on 14.01.2017.
 */

public class RetrofitAllPhotosRepo extends RetrofitRepo
        implements IAllPhotosRepo {

    private static final int PHOTOS_TO_LOAD = 200;

    private PhotosService photosService;

    public RetrofitAllPhotosRepo() {
        this.photosService = VkClient.makeService(PhotosService.class);
    }

    @Override
    public void getAllPhotos(long id, int offset, final Result<ArrayList<Photo>> onSuccess,
                             Result<VkError> onError) {
        photosService.getAll(
                id,
                BooleanInt.TRUE,
                BooleanInt.TRUE,
                offset,
                PHOTOS_TO_LOAD
        ).enqueue(new RetrofitRepo.VkCallback<>(new Repo.Result<AllPhotosResponse>() {
            @Override
            public void response(AllPhotosResponse allPhotosResponse) {
                onSuccess.response(allPhotosResponse.getResponse().getItems());

            }
        }, onError));


    }
}

