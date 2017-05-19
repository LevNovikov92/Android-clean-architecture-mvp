package com.lev.mvpcleanarch.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lev.mvpcleanarch.data.entity.TaskEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Author: Lev
 * Date: 15.05.2017
 */

public class TaskJsonMapper {

    private final Gson mGson;

    @Inject
    public TaskJsonMapper() {
        mGson = new Gson();
    }

    public List<TaskEntity> fromJson(String json) throws JsonSyntaxException {
        final Type taskListType = new TypeToken<List<TaskEntity>>() {}.getType();
        final List<TaskEntity> tasks = mGson.fromJson(json, taskListType);
        return tasks != null ? tasks : new ArrayList<>();
    }
}
