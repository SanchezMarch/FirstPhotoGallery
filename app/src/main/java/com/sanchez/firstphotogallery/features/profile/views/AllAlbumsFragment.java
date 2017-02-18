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
import com.sanchez.firstphotogallery.common.model.albums.AlbumItem;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.repo.Repo;
import com.sanchez.firstphotogallery.features.prefs.Preferences;
import com.sanchez.firstphotogallery.features.profile.adapters.AllAlbumsAdapter;
import com.sanchez.firstphotogallery.features.profile.repository.IAllAlbumsRepo;
import com.sanchez.firstphotogallery.features.profile.repository.RetrofitAllAlbumsRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олександр on 24.12.2016.
 */

public class AllAlbumsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AllAlbumsAdapter adapter;
    private IAllAlbumsRepo allAlbumsRepo = new RetrofitAllAlbumsRepo();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_albums, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rvAlbums);
        initRecycler();

        loadAlbums();

        return view;
    }

    private void loadAlbums() {
        allAlbumsRepo.getAlbums(
                Preferences.with(getActivity()).getUser(), 0,
                new Repo.Result<ArrayList<AlbumItem>>() {
                    @Override
                    public void response(ArrayList<AlbumItem> albumItems) {
                        onAlbumsLoaded(albumItems);
                    }
                }, new Repo.Result<VkError>() {
                    @Override
                    public void response(VkError error) {

                    }
                }
        );
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        this.adapter = new AllAlbumsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void onAlbumsLoaded(List<AlbumItem> albums) {
        adapter.add(albums);
    }
}
