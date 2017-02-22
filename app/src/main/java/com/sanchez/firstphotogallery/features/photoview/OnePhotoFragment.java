package com.sanchez.firstphotogallery.features.photoview;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.photos.Photo;

public class OnePhotoFragment extends Fragment {

    private View view;
    private SimpleDraweeView sdvPhoto;
    private TextView tvTags, tvComments, tvReposts;
    private CheckBox cbLike;
    private Photo photo;
    private Toolbar toolbar;

    public static OnePhotoFragment newInstance() {
        return new OnePhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_one_photo, container, false);

        sdvPhoto = (SimpleDraweeView) view.findViewById(R.id.sdvPhoto);
        tvTags = (TextView) view.findViewById(R.id.tvTags);
        cbLike = (CheckBox) view.findViewById(R.id.cbLike);
        tvReposts = (TextView) view.findViewById(R.id.tvReposts);
        tvComments = (TextView) view.findViewById(R.id.tvComments);


        updateDataUI();
        return view;
    }

    public void setData(Photo photo) {
        this.photo = photo;
    }

    private void updateDataUI() {
        sdvPhoto.setImageURI(photo.getPhoto604());
        tvTags.setText(photo.getText());
        System.out.println("DEBUG: " + photo.getLikes().count);
        if (photo.getLikes().userLikes == 1)
            cbLike.setChecked(true);
        cbLike.setText(photo.getLikes().count + " " + getActivity().getString(R.string.photo_likes));

        tvReposts.setText(photo.getReposts().count + " " + getActivity().getString(R.string.photo_reposts));
       // tvComments.setText(photo.getComments().count + " " + getActivity().getString(R.string.photo_comments));

    }

}
