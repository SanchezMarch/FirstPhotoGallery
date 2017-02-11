package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.photos.Photo;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.repo.Repo;

import java.util.ArrayList;

/**
 * Created by Олександр on 14.01.2017.
 */

public interface IAllPhotosRepo {
    void getAllPhotos(long id, int offset,
                      final Repo.Result<ArrayList<Photo>> onSuccess,
                      final Repo.Result<VkError> onError);
}
