package com.sanchez.firstphotogallery.features.photoview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.model.photos.Photo;
import com.sanchez.firstphotogallery.features.photoview.adapters.PhotoPageAdapter;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    private ViewPager photoViewerViewPager;
    private ArrayList<Photo> photoList;
    private int numberOfPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photoList = (ArrayList<Photo>) this.getIntent().getSerializableExtra("showPhotos");
        numberOfPhotos = getIntent().getIntExtra("numberOfPhotos", 0);

        photoViewerViewPager = (ViewPager) findViewById(R.id.photoViewerViewPager);
        photoViewerViewPager.setAdapter(new PhotoPageAdapter(getSupportFragmentManager(), photoList));
        photoViewerViewPager.setCurrentItem(numberOfPhotos);
    }
}
