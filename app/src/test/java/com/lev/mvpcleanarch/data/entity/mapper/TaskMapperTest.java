package com.lev.mvpcleanarch.data.entity.mapper;

import com.google.gson.JsonSyntaxException;
import com.lev.mvpcleanarch.data.entity.TaskEntity;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

/**
 * Author: Lev
 * Date: 15.05.2017
 */
public class TaskMapperTest {
    @Test
    public void fromJson() {
        String json =
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

        final TaskMapper mapper = new TaskMapper();
        List<TaskEntity> tasks = mapper.fromJson(json);

        Assert.assertEquals(3, tasks.size());
        Assert.assertEquals("title1", tasks.get(0).getTitle());
        Assert.assertEquals("description2", tasks.get(1).getDescription());
        Assert.assertEquals(true, tasks.get(2).isCompleted());

        tasks = mapper.fromJson("");
        Assert.assertEquals(0, tasks.size());

        tasks = mapper.fromJson("[]");
        Assert.assertEquals(0, tasks.size());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testInvalidJson() {
        new TaskMapper().fromJson("invalid json");
    }

}