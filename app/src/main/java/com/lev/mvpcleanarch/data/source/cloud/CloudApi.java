package com.lev.mvpcleanarch.data.source.cloud;

import android.support.annotation.Nullable;

import com.lev.mvpcleanarch.exception.ApiRequestException;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public interface CloudApi {

    Response request(Request request) throws IOException;

    Response get(String url, Headers headers) throws IOException ;

    String requestJson(String method, @Nullable RequestBody body, String path)
            throws IOException, ApiRequestException;

    String jsonGet(String path) throws IOException, ApiRequestException;
}
