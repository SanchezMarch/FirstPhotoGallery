package com.sanchez.firstphotogallery.features.profile.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олександр on 26.12.2016.
 */


public class AllPhotosAdapter extends RecyclerView.Adapter<AllPhotosAdapter.PhotoViewHolder> {

    private List<Photo> allPhotos = new ArrayList<>();

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(
                View.inflate(parent.getContext(), R.layout.item_photo, parent));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.sdvThumb.setImageURI(allPhotos.get(position).getThumb());

    }

    @Override
    public int getItemCount() {
        return allPhotos.size();
    }

    public void add(List<Photo> allPhotos) {
        allPhotos.addAll(allPhotos);
        notifyDataSetChanged();
    }


    class PhotoViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView sdvThumb;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            sdvThumb = (SimpleDraweeView) itemView.findViewById(R.id.sdvPhoto);
        }

    }


// https://inducesmile.com/android/android-gridlayoutmanager-with-recyclerview-in-material-design/
//https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView
//https://developer.android.com/samples/RecyclerView/src/com.example.android.recyclerview/RecyclerViewFragment.html
}
