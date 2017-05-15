package com.lev.mvpcleanarch.data.repositories;

import com.lev.mvpcleanarch.data.entity.Task;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class TaskRepository extends BaseRepository<Task> {

    public TaskRepository() {
        super(Task.class);
    }
}
