package com.lev.mvpcleanarch.data.source.cloud;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
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
    private final String json =
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

    @Before
    public void prepareTesting() throws IOException {
        final MockWebServer server = new MockWebServer();
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().equals("/task/")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(json);
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
        final Response response = api.request(request);
        final ResponseBody body = response.body();
        Assert.assertEquals(200, response.code());
        Assert.assertNotNull(body);
        Assert.assertEquals(json, body.string());
    }

    @Test
    public void get() throws Exception {
        final Response response = api.get(baseUrl + "task/", new Headers.Builder().build());
        Assert.assertEquals(200, response.code());
        final ResponseBody body = response.body();
        Assert.assertNotNull(body);
        Assert.assertEquals(json, body.string());
    }

    @Test
    public void getJson() throws Exception {
        final String responseJson = api.jsonGet("task/");
        Assert.assertEquals(json, responseJson);
    }

}