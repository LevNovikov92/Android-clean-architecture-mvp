package com.lev.mvpcleanarch.data.source.cloud;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class ApiImpl implements Api {

    @SuppressWarnings("WeakerAccess")
    @Inject
    public Context context;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public OkHttpClient client;

    @Override
    public Response request(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    @Override
    public Response get(String url, Headers headers) throws IOException {
        final Request request = new Request.Builder()
                .method("GET", null)
                .url(url)
                .headers(headers)
                .build();
        return client.newCall(request).execute();
    }
}
