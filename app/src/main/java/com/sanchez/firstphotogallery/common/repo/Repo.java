package com.sanchez.firstphotogallery.common.repo;

/**
 * Created by Олександр on 13.01.2017.
 */

public abstract class Repo {

    public interface Result<T>{
        void response(T t);
    }
}
