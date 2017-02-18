package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.albums.AlbumItem;
import com.sanchez.firstphotogallery.common.model.albums.AlbumResponse;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.network.api.client.VkClient;
import com.sanchez.firstphotogallery.common.network.api.services.PhotosService;
import com.sanchez.firstphotogallery.common.network.api.utils.BooleanInt;
import com.sanchez.firstphotogallery.common.repo.Repo;
import com.sanchez.firstphotogallery.common.repo.RetrofitRepo;

import java.util.ArrayList;

/**
 * Created by Олександр on 11.02.2017.
 */

public class RetrofitAllAlbumsRepo extends RetrofitRepo
        implements IAllAlbumsRepo {

    private static final int ALBUMS_TO_LOAD = 20;

    private PhotosService photosService;

    public RetrofitAllAlbumsRepo() {
        this.photosService = VkClient.makeService(PhotosService.class);
    }


    @Override
    public void getAlbums(long id, int offset, final Result<ArrayList<AlbumItem>> onSuccess, Result<VkError> onError) {
        photosService.getAlbums(
                id,
                BooleanInt.TRUE,
                BooleanInt.TRUE,
                offset,
                ALBUMS_TO_LOAD
        ).enqueue(new RetrofitRepo.VkCallback<>(new Repo.Result<AlbumResponse>() {
            @Override
            public void response(AlbumResponse albumResponse) {
                onSuccess.response(albumResponse.getResponse().getItems());

            }
        }, onError));


    }
}
