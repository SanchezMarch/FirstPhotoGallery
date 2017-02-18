package com.sanchez.firstphotogallery.common.network.api.client;

import com.google.gson.Gson;
import com.sanchez.firstphotogallery.App;
import com.sanchez.firstphotogallery.common.network.api.utils.EnumGsonConverterFactory;
import com.sanchez.firstphotogallery.features.prefs.Preferences;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Олександр on 16.12.2016.
 */

public class VkClient implements Interceptor {

    private static final String QUERY_API_VERSION = "v";
    private static final String QUERY_ACCESS_TOKEN = "access_token";

    private Retrofit retrofit;
    private Object service;


    public static VkClient instance = new VkClient();

    public static VkClient getInstance() {
        return instance;
    }

    private VkClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(this)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addConverterFactory(new EnumGsonConverterFactory())
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl.Builder urlBuilder = originalUrl.newBuilder();
        urlBuilder.addQueryParameter(QUERY_API_VERSION, Config.VERSION);
        urlBuilder.addQueryParameter(QUERY_ACCESS_TOKEN, Preferences.with(App.getContext()).getAccessToken());

        Request.Builder requestBuilder = original.newBuilder()
                .url(urlBuilder.build());

        Request request = requestBuilder.build();

        return chain.proceed(request);
    }

    @SuppressWarnings("unchecked")
    public static <T> T makeService(Class<T> serviceClass) {
        VkClient client = getInstance();
        if ((client.service == null || !serviceClass.isInstance(client.service))) {
            client.service = client.retrofit.create(serviceClass);
        }

        return (T) client.service;
    }
}
