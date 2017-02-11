package com.sanchez.firstphotogallery.common.model.responses.base;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Олександр on 13.01.2017.
 */

public class VkPaginalResponse<T> extends VkResponse<VkPaginalResponse<T>> {

    @SerializedName("count")
    private int count;

    @SerializedName("items")
    private ArrayList<T> items;

    public int getCount(){
        return count;
    }

    public ArrayList<T> getItems() {
        return items;
    }
}
