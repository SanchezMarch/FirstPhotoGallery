package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.albums.AlbumItem;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.repo.Repo;

import java.util.ArrayList;

/**
 * Created by Олександр on 11.02.2017.
 */

public interface IAllAlbumsRepo {
    void getAlbums(long id, int offset,
                   final Repo.Result<ArrayList<AlbumItem>> onSuccess,
                   final Repo.Result<VkError> onError);
}
