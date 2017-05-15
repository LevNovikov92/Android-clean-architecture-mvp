package com.lev.mvpcleanarch.data.source.cloud;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public interface Api {

    Response request(Request request) throws IOException;

    Response get(String url, Headers headers) throws IOException ;
}
