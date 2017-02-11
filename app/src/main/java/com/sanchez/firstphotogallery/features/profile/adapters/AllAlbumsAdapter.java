package com.sanchez.firstphotogallery.features.profile.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.albums.AlbumItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олександр on 26.12.2016.
 */

public class AllAlbumsAdapter extends RecyclerView.Adapter<AllAlbumsAdapter.AlbumsViewHolder>{

    private List<AlbumItem> photoAlbums = new ArrayList<>();


    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return new AlbumsViewHolder(View.inflate(parent.getContext(), R.layout.item_photo_album, parent));
    }

    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, int position) {
        holder.sdvThumb.setImageURI(photoAlbums.get(position).getThumb_src());
        holder.name.setText(photoAlbums.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return photoAlbums.size();
    }

    public void add(List<AlbumItem> albums) {
        photoAlbums.addAll(albums);
        notifyDataSetChanged();
    }





    public class AlbumsViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        public SimpleDraweeView sdvThumb;
        public TextView name;
        private IOnItemClickListener myOnItemClickListener;

        public AlbumsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            sdvThumb = (SimpleDraweeView) itemView.findViewById(R.id.sdvPhotoAlbum);
            name = (TextView) itemView.findViewById(R.id.albumName);
        }

        @Override
        public void onClick(View v) {
            if (myOnItemClickListener != null) {
                myOnItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }

        public void setMyOnItemClickListener(IOnItemClickListener myOnItemClickListener) {
            this.myOnItemClickListener = myOnItemClickListener;
        }
    }

}






