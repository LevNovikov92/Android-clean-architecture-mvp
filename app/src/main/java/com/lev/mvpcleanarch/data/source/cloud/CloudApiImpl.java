package com.lev.mvpcleanarch.data.source.cloud;

import android.content.Context;
import android.support.annotation.Nullable;

import com.lev.mvpcleanarch.data.exception.ApiRequestException;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class CloudApiImpl implements CloudApi {

    private final String mBaseUrl;

    public CloudApiImpl(String url) {
        mBaseUrl = url;
    }

    @SuppressWarnings("WeakerAccess")
    @Inject
    public Context context;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public OkHttpClient client;

    Response request(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    Response get(String url, Headers headers) throws IOException {
        final Request request = new Request.Builder()
                .method("GET", null)
                .url(url)
                .headers(headers)
                .build();
        return client.newCall(request).execute();
    }

    private Observable<String> requestJson(String method, @Nullable RequestBody body, String path) {
        return Observable.create(emitter -> {
            final Request request = new Request.Builder()
                    .method(method, body)
                    .url(getUrl(path))
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                final ResponseBody responseBody = response.body();
                if (response.code() != 200) {
                    throw new ApiRequestException(response.code(), responseBody != null ?
                            responseBody.string() : "");
                }
                emitter.onNext(responseBody != null ? responseBody.string() : "");
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            } finally {
                Util.closeQuietly(response);
            }
        });
    }

    @Override
    public Observable<String> jsonGet(String path) {
        return requestJson("GET", null, path);
    }

    private String getUrl(String path) {
        return mBaseUrl + path;
    }
}
