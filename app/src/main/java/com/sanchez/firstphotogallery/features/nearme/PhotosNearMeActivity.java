package com.sanchez.firstphotogallery.features.nearme;


import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.common.location.Accuracy;
import com.sanchez.firstphotogallery.common.location.LocationChangedListener;
import com.sanchez.firstphotogallery.common.location.LocationHelper;
import com.sanchez.firstphotogallery.common.model.photos.PhotoGeoItem;
import com.sanchez.firstphotogallery.common.model.responses.errors.VkError;
import com.sanchez.firstphotogallery.common.model.responses.photos.PhotosGeoResponse;
import com.sanchez.firstphotogallery.common.network.api.client.VkClient;
import com.sanchez.firstphotogallery.common.network.api.services.PhotosService;
import com.sanchez.firstphotogallery.common.repo.Repo;
import com.sanchez.firstphotogallery.common.repo.RetrofitRepo;
import com.sanchez.firstphotogallery.features.places.PlacesActivity;

import java.util.ArrayList;

public class PhotosNearMeActivity extends AppCompatActivity
        implements OnMapReadyCallback, LocationChangedListener {

    private static final int REQUEST_CODE_PLACES = 0;

    private GoogleMap googleMap;

    private LocationHelper locationHelper;

    private double longitude, latitude;

    Location myCurrentLocation;

    Marker currentLocationMarker;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_near_me);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map))
                .getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (googleMap != null) {
            switch (item.getItemId()) {
                case R.id.action_search_by_location:
     //              showPhotosOnMap();
                    break;

                case R.id.search_anywhere:
                    startActivityForResult(new Intent(this, PlacesActivity.class), REQUEST_CODE_PLACES);
                    break;

                case android.R.id.home:
                    onBackPressed();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        locationHelper = new LocationHelper(this);
        locationHelper.setLocationListener(this);
        locationHelper.startLocationUpdates(Accuracy.HIGH);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_PLACES:
                    Bundle extras = data.getExtras();
                    this.longitude = extras.getDouble(PlacesActivity.KEY_LONGITUDE, 0.0f);
                    this.latitude = extras.getDouble(PlacesActivity.KEY_LATITUDE, 0.0f);
                    //        loadPhoto();
                    break;
            }
            loadPhotos(this.latitude, this.longitude);

            Log.d("MAPSMAPSMAPS", "onActivityResult: lat: " + latitude + ", lon: " + longitude);
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        locationHelper.stopLocationUpdates();
        myCurrentLocation = location;

        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("You are here");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currentLocationMarker = googleMap.addMarker(markerOptions);

        Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();

        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(14).build();

        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        //If you only need one location, unregister the listener
        //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

    //        showPhotosOnMap();
    }

    private void loadPhotos(double latitude, double longitude){
        VkClient.makeService(PhotosService.class).getPhotosMapSearch(
                latitude,
                longitude,
                0,
                500,
                5000
        ).enqueue(new RetrofitRepo.VkCallback<>(new Repo.Result<PhotosGeoResponse>() {
            @Override
            public void response(PhotosGeoResponse photosGeoResponse) {
                showPhotosOnMap(photosGeoResponse.getResponse().getItems());
            }
        }, new Repo.Result<VkError>() {
            @Override
            public void response(VkError error) {

            }
        }
        ));
    }

    private void showPhotosOnMap(ArrayList<PhotoGeoItem> photos){
        googleMap.clear();

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.latitude, this.longitude), 17));

        for (PhotoGeoItem photo : photos) {
            LatLng latLng = new LatLng(photo.getLatitude(), photo.getLongitude());

            googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_photo)));
        }
    }

}
