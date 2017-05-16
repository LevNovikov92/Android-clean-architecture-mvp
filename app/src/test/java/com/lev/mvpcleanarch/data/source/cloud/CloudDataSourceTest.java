package com.lev.mvpcleanarch.data.source.cloud;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
import com.lev.mvpcleanarch.data.entity.mapper.TaskMapper;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Author: Lev
 * Date: 15.05.2017
 */
public class CloudDataSourceTest {

    private CloudApiImpl api;
    private static final String json =
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
        String baseUrl = server.url("/").toString();
        api = new CloudApiImpl(baseUrl);
        api.client = new OkHttpClient();
    }

    @Test
    public void getTasks() throws Exception {
        final CloudDataSource source = new CloudDataSource();
        source.taskMapper = new TaskMapper();
        source.api = api;

        final TestObserver<List<TaskEntity>> observer = source.getTasks().test();
        final List<TaskEntity> tasks = observer.values().get(0);
        observer.assertComplete();
        observer.assertValueCount(1);
        Assert.assertEquals(3, tasks.size());
        Assert.assertEquals("title1", tasks.get(0).getTitle());
        Assert.assertEquals("description2", tasks.get(1).getDescription());
        Assert.assertEquals(true, tasks.get(2).isCompleted());
    }

}