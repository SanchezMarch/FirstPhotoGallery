package com.sanchez.firstphotogallery.common.network.api.utils;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Олександр on 14.01.2017.
 */

public class EnumGsonConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<?, String> converter = null;
        if (type instanceof Class && ((Class<?>) type).isEnum()) {
            converter = new Converter<Object, String>() {
                @Override
                public String convert(Object value) throws IOException {
                    return getSerializedNameValue((Enum) value);
                }
            };
        }
        return converter;
    }

    private <E extends Enum<E>> String getSerializedNameValue(E e) {
        try {
            return e.getClass().getField(e.name()).getAnnotation(SerializedName.class).value();
        } catch (Exception exception) {
            return e.name();
        }
    }
}
