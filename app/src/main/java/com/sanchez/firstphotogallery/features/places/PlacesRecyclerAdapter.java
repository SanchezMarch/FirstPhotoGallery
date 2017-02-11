package com.sanchez.firstphotogallery.features.places;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;
import com.sanchez.firstphotogallery.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Олександр on 05.02.2017.
 */

public class PlacesRecyclerAdapter
        extends RecyclerView.Adapter<PlacesRecyclerAdapter.PredictionViewHolder>
        implements Filterable {

    private static final int MIN_CHARS_COUNT = 3;
    private static final int TYPE_FILTER = AutocompleteFilter.TYPE_FILTER_ADDRESS;

    private Context context;
    private GoogleApiClient googleApiClient;
    private LatLngBounds bounds;
    private ArrayList<AutocompletePrediction> resultsList = new ArrayList<>();
    private AutocompleteFilter filter;
    private CharacterStyle styleHighlight;

    private OnPlaceSelectedListener placeSelectedListener;

    public PlacesRecyclerAdapter(Context context, GoogleApiClient googleApiClient, LatLngBounds bounds) {
        this.context = context;
        this.googleApiClient = googleApiClient;
        this.bounds = bounds;
        this.filter = new AutocompleteFilter.Builder()
                .setTypeFilter(TYPE_FILTER)
                .build();
        this.styleHighlight = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    public PredictionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PredictionViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(PlacesRecyclerAdapter.PredictionViewHolder holder, int position) {
        holder.tvSearchPlace.setText(resultsList.get(position).getFullText(styleHighlight));
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() >= MIN_CHARS_COUNT) {
                    resultsList = getAutocomplete(constraint);
                    if (resultsList != null) {
                        results.values = resultsList;
                        results.count = resultsList.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

    public void setPlaceSelectedListener(OnPlaceSelectedListener placeSelectedListener) {
        this.placeSelectedListener = placeSelectedListener;
    }

    private ArrayList<AutocompletePrediction> getAutocomplete(CharSequence constraint) {
        if (googleApiClient.isConnected()) {
            PendingResult<AutocompletePredictionBuffer> results =
                    Places.GeoDataApi
                            .getAutocompletePredictions(googleApiClient, constraint.toString(),
                                    bounds, filter);

            AutocompletePredictionBuffer autocompletePredictions = results
                    .await(60, TimeUnit.SECONDS);


            final Status status = autocompletePredictions.getStatus();
            if (!status.isSuccess()) {
                autocompletePredictions.release();
                return new ArrayList<>();
            }

            return DataBufferUtils.freezeAndClose(autocompletePredictions);
        }
        return new ArrayList<>();
    }

    class PredictionViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView tvSearchPlace;


        public PredictionViewHolder(View itemView) {
            super(itemView);
            tvSearchPlace = (TextView) itemView.findViewById(R.id.tvSearchPlace);
            tvSearchPlace.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (placeSelectedListener != null) {
                placeSelectedListener.onPlaceSelected(
                        resultsList.get(getAdapterPosition()).getPlaceId()
                );
            }

        }
    }

    public interface OnPlaceSelectedListener {
        void onPlaceSelected(String placeId);
    }
}
