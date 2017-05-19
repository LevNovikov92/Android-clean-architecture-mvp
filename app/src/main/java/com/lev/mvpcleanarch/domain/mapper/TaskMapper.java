package com.lev.mvpcleanarch.domain.mapper;

import android.support.annotation.Nullable;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
import com.lev.mvpcleanarch.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Lev
 * Date: 17.05.2017
 */

public class TaskMapper {

    //TODO? What's best: static methods or inject single instance?
    @SuppressWarnings("WeakerAccess")
    @Nullable
    public static Task map(@Nullable TaskEntity taskEntity) {
        if (taskEntity == null) return null;
        final Task task = new Task(taskEntity.getId());
        task.setTitle(taskEntity.getTitle());
        task.setDescription(taskEntity.getDescription());
        task.setCompleted(taskEntity.isCompleted());
        return task;
    }

    @Nullable
    public static List<Task> mapList(@Nullable List<TaskEntity> taskEntities) {
        if (taskEntities == null) return null;
        final List<Task> tasks = new ArrayList<>();
        for (TaskEntity entity : taskEntities) {
            tasks.add(map(entity));
        }
        return tasks;
    }
}
