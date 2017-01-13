package com.sanchez.firstphotogallery.common.model.responses.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 13.01.2017.
 */

public abstract class VkResponse<T> {

    @SerializedName("response")
    private T response;

    @SerializedName("error")
    private Error error;

    public T getResponse(){
        return response;
    }

    public boolean isSuccessfull(){
        return error == null;
    }

    public Error getError(){
        return error;
    }
}
