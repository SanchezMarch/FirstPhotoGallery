package com.sanchez.firstphotogallery.features.profile.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.PhotoAlbum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олександр on 26.12.2016.
 */

public class AllAlbumsAdapter extends RecyclerView.Adapter<AllAlbumsAdapter.AlbumsViewHolder>{

    private List<PhotoAlbum> photoAlbums = new ArrayList<>();

    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumsViewHolder(View.inflate(parent.getContext(), R.layout.item_photo_album, parent));
    }

    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, int position) {
        holder.sdvThumb.setImageURI(photoAlbums.get(position).getThumb());
    }

    @Override
    public int getItemCount() {
        return photoAlbums.size();
    }

    public void add(List<PhotoAlbum> albums) {
        photoAlbums.addAll(albums);
        notifyDataSetChanged();
    }

    class AlbumsViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView sdvThumb;

        public AlbumsViewHolder(View itemView) {
            super(itemView);

            sdvThumb = (SimpleDraweeView) itemView.findViewById(R.id.sdvPhotoAlbum);
        }
    }

}






