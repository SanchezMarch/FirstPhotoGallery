package com.sanchez.firstphotogallery.features.profile.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.Photo;
import com.sanchez.firstphotogallery.features.profile.adapters.AllPhotosAdapter;


import java.util.List;

/**
 * Created by Олександр on 24.12.2016.
 */

public class AllPhotosFragment extends Fragment {

    private AllPhotosAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_photos, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rvPhotos);

        return view;
    }

    private void ititRecycler() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.adapter = new AllPhotosAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void onPhotosLoaded(List<Photo> albums) {
        adapter.add(albums);
    }

}
