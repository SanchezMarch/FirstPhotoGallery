package com.sanchez.firstphotogallery.common.model.responses.errors;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 13.01.2017.
 */

public class Error {
    @SerializedName("error_code")
    private int errorCode;

    @SerializedName("error_message")
    private String errorMessage;

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
