package com.sanchez.firstphotogallery.features.photoview.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.sanchez.firstphotogallery.common.model.photos.Photo;
import com.sanchez.firstphotogallery.features.photoview.OnePhotoFragment;

import java.util.ArrayList;

/**
 * Created by Олександр on 29.01.2017.
 */

public class PhotoPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Photo> photoList;

    public PhotoPageAdapter(FragmentManager fm, ArrayList<Photo> photoList) {
        super(fm);
        this.photoList = photoList;
        System.out.println("DEBUG: List size = "+photoList.size());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        fragment = OnePhotoFragment.newInstance();
        ((OnePhotoFragment)fragment).setData(photoList.get(position));

        return fragment;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }
}
