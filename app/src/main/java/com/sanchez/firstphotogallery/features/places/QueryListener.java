package com.sanchez.firstphotogallery.features.places;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Олександр on 05.02.2017.
 */

public abstract class QueryListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        onQueryChanged(editable.toString());
    }

    public abstract void onQueryChanged(String query);
}

