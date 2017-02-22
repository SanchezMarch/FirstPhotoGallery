package com.sanchez.firstphotogallery.features.profile.adapters;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.photos.Photo;
import com.sanchez.firstphotogallery.features.photoview.PhotoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олександр on 26.12.2016.
 */


public class AllPhotosAdapter extends RecyclerView.Adapter<AllPhotosAdapter.PhotoViewHolder> {

    private ArrayList<Photo> photoList = new ArrayList<>();

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        holder.sdvThumb.setImageURI(getPhoto(position));

    }

    @Override
    public int getItemCount() {
        return this.photoList.size();
    }

    public void add(List<Photo> allPhotos) {
        this.photoList.addAll(allPhotos);
        notifyDataSetChanged();
    }

    private String getPhoto(int position) {
        String url = "";

        url = photoList.get(position).getPhoto807();
        if (url != null) return url;

        url = photoList.get(position).getPhoto604();
        if (url != null) return url;

        url = photoList.get(position).getPhoto130();
        if (url != null) return url;

        url = photoList.get(position).getPhoto75();
        if (url != null) return url;

        return "";
    }


    class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SimpleDraweeView sdvThumb;


        public PhotoViewHolder(View itemView) {
            super(itemView);

            sdvThumb = (SimpleDraweeView) itemView.findViewById(R.id.sdvPhoto);
            sdvThumb.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), PhotoActivity.class);
            intent.putParcelableArrayListExtra(PhotoActivity.KEY_PHOTOS_LIST, photoList);
            v.getContext().startActivity(intent);
        }
    }


// https://inducesmile.com/android/android-gridlayoutmanager-with-recyclerview-in-material-design/
//https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView
//https://developer.android.com/samples/RecyclerView/src/com.example.android.recyclerview/RecyclerViewFragment.html
}
