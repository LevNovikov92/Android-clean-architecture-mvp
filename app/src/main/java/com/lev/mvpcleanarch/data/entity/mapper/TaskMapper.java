package com.lev.mvpcleanarch.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lev.mvpcleanarch.data.entity.Task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Lev
 * Date: 15.05.2017
 */

public class TaskMapper {

    private final Gson mGson;

    public TaskMapper() {
        mGson = new Gson();
    }

    public List<Task> fromJson(String json) throws JsonSyntaxException {
        final Type taskListType = new TypeToken<List<Task>>() {}.getType();
        final List<Task> tasks = mGson.fromJson(json, taskListType);
        return tasks != null ? tasks : new ArrayList<>();
    }
}
