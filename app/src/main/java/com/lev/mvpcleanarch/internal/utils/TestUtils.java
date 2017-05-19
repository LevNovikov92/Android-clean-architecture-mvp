package com.lev.mvpcleanarch.internal.utils;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
import com.lev.mvpcleanarch.domain.Task;

/**
 * Author: Lev
 * Date: 18.05.2017
 */

public class TestUtils {

    public static TaskEntity getTaskEntity(String id, String title, String desc, boolean isCompleted) {
        final TaskEntity task = new TaskEntity();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(desc);
        task.setCompleted(isCompleted);
        return task;
    }

    public static Task getTask(String id, String title, String desc, boolean isCompleted) {
        final Task task = new Task(id);
        task.setTitle(title);
        task.setDescription(desc);
        task.setCompleted(isCompleted);
        return task;
    }
}
