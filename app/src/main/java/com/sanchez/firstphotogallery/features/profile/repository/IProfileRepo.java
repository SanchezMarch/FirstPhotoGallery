package com.sanchez.firstphotogallery.features.profile.repository;

import com.sanchez.firstphotogallery.common.model.user.User;
import com.sanchez.firstphotogallery.common.repo.Repo;

/**
 * Created by Олександр on 13.01.2017.
 */

public interface IProfileRepo {

    void getProfile(long id,
                    Repo.Result<User> onSuccess,
                    Repo.Result<Throwable> onError);
}
