package com.sanchez.firstphotogallery.features.nearme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.features.places.PlacesActivity;

public class PhotosNearMeActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private static final int REQUEST_CODE_PLACES = 0;

    private GoogleMap googleMap;

    private double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_near_me);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
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
                    break;

                case R.id.search_anywhere:
                    startActivityForResult(new Intent(this, PlacesActivity.class), REQUEST_CODE_PLACES);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
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
                    break;
            }
            LatLng position = new LatLng(latitude, longitude);

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17));
            googleMap.addMarker(new MarkerOptions().position(position).title("Tapped here"));

            Log.d("MAPSMAPSMAPS", "onActivityResult: lat: " + latitude + ", lon: " +  longitude);
        }
    }
}
