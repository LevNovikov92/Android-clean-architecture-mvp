package com.lev.mvpcleanarch.data.source.cloud;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.observers.TestObserver;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Author: Lev
 * Date: 15.05.2017
 */
public class CloudApiImplTest {

    private CloudApiImpl api;
    private String baseUrl;
    private static final String jsonGet =
            "[" +
                    "{" +
                    "\"title\": \"title1\"," +
                    "\"description\": \"description1\"," +
                    "\"isCompleted\": true" +
                    "}," +
                    "{" +
                    "\"title\": \"title2\"," +
                    "\"description\": \"description2\"," +
                    "\"isCompleted\": false" +
                    "}," +
                    "{" +
                    "\"title\": \"title3\"," +
                    "\"description\": \"description3\"," +
                    "\"isCompleted\": true" +
                    "}" +
                    "]";
    private static final String jsonPost =
            "{\"result\":\"ok\"}";


    @Before
    public void prepareTesting() throws IOException {
        final MockWebServer server = new MockWebServer();
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getMethod().equalsIgnoreCase("GET") && request.getPath().equals("/task/")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(jsonGet);
                } else if (request.getMethod().equalsIgnoreCase("POST") &&
                                request.getPath().equals("/task/")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(jsonPost);
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        server.start();
        baseUrl = server.url("/").toString();
        api = new CloudApiImpl(baseUrl);
        api.client = new OkHttpClient();
    }

    @Test
    public void request() throws Exception {
        final Request request = new Request.Builder()
                .method("GET", null)
                .url(baseUrl + "task/")
                .build();
        Response response = null;
        try {
            response = api.request(request);
            final ResponseBody body = response.body();
            Assert.assertEquals(200, response.code());
            Assert.assertNotNull(body);
            Assert.assertEquals(jsonGet, body.string());
        } finally {
            Util.closeQuietly(response);
        }
    }

    @Test
    public void requestPost() throws Exception {
        final Request request = new Request.Builder()
                .method("POST", null)
                .url(baseUrl + "task/")
                .build();
        Response response = null;
        try {
            response = api.request(request);
            final ResponseBody body = response.body();
            Assert.assertEquals(200, response.code());
            Assert.assertNotNull(body);
            Assert.assertEquals(jsonPost, body.string());
        } finally {
            Util.closeQuietly(response);
        }
    }

    @Test
    public void get() throws Exception {
        Response response = null;
        try {
            response = api.get(baseUrl + "task/", new Headers.Builder().build());
            Assert.assertEquals(200, response.code());
            final ResponseBody body = response.body();
            Assert.assertNotNull(body);
            Assert.assertEquals(jsonGet, body.string());
        } finally {
            Util.closeQuietly(response);
        }
    }

    @Test
    public void getJson() throws Exception {
        final TestObserver<String> observer = api.jsonGet("task/").test();
        observer.assertComplete();
        observer.assertValueCount(1);
        Assert.assertEquals(jsonGet, observer.values().get(0));
    }

}