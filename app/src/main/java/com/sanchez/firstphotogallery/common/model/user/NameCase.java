package com.sanchez.firstphotogallery.common.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Олександр on 13.01.2017.
 */

public enum NameCase {

    @SerializedName("nom")
    NOMINATIVE,

    @SerializedName("gen")
    GENITIVE,

    @SerializedName("dat")
    DATIVE,

    @SerializedName("acc")
    ACCUSATIVE,

    @SerializedName("ins")
    ABLATIVE,

    @SerializedName("abl")
    PREPOSITIONAL
}
