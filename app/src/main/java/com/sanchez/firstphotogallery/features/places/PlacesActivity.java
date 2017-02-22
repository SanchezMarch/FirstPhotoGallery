package com.sanchez.firstphotogallery.features.places;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.sanchez.firstphotogallery.R;

public class PlacesActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, PlacesRecyclerAdapter.OnPlaceSelectedListener {

    public static final String KEY_NAME = "NAME";
    public static final String KEY_ADDRESS = "ADDRESS";
    public static final String KEY_LONGITUDE = "LONGITUDE";
    public static final String KEY_LATITUDE = "LATITUDE";

    private EditText tvSearchPlace;
    private RecyclerView rvResults;

    private GoogleApiClient googleApiClient;
    private PlacesRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        tvSearchPlace = (EditText) findViewById(R.id.tvSearchPlace);
        rvResults = (RecyclerView) findViewById(R.id.rvResults);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onPlaceSelected(String placeId) {
        Places.GeoDataApi
                .getPlaceById(googleApiClient, placeId)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        Place place = places.get(0);

                        Intent intent = new Intent();
                        intent.putExtra(KEY_NAME, place.getName());
                        intent.putExtra(KEY_ADDRESS, place.getAddress());
                        intent.putExtra(KEY_LONGITUDE, place.getLatLng().longitude);
                        intent.putExtra(KEY_LATITUDE, place.getLatLng().latitude);
                        setResult(RESULT_OK, intent);

                        places.release();
                        finish();
                    }
                });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        init();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    private void init() {
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setItemAnimator(new DefaultItemAnimator());

        adapter = new PlacesRecyclerAdapter(this, googleApiClient, null);
        adapter.setPlaceSelectedListener(this);
        rvResults.setAdapter(adapter);

        tvSearchPlace.addTextChangedListener(new QueryListener() {
            @Override
            public void onQueryChanged(String query) {
                adapter.getFilter().filter(query);
            }
        });

    }

}

