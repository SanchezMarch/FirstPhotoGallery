package com.sanchez.firstphotogallery.common.model.responses.base;

import com.google.gson.annotations.SerializedName;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;

/**
 * Created by Олександр on 13.01.2017.
 */

public abstract class VkResponse<T> {

    @SerializedName("response")
    private T response;

    @SerializedName("error")
    private VkError error;

    public T getResponse() {
        return response;
    }

    public boolean isSuccessfull() {
        return error == null;
    }

    public VkError getError() {
        return error;
    }
}
