package com.sanchez.firstphotogallery.common.model.responses.user;

import com.sanchez.firstphotogallery.common.model.responses.base.VkResponse;
import com.sanchez.firstphotogallery.common.model.user.User;

import java.util.ArrayList;

/**
 * Created by Олександр on 13.01.2017.
 */

public class UsersResponse extends VkResponse<ArrayList<User>> {

    public User getFirst() {
        if (getResponse().size() > 0) {
            return getResponse().get(0);
        } else return null;
    }

}
