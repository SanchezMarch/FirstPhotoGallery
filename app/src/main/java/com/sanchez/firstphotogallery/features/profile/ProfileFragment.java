package com.sanchez.firstphotogallery.features.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;

/**
 * Created by Олександр on 19.12.2016.
 */

public class ProfileFragment extends Fragment {

    private SimpleDraweeView sdvAvatar;

    public ProfileFragment(){

    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container);

        sdvAvatar = (SimpleDraweeView) v.findViewById(R.id.sdvAvatar);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
