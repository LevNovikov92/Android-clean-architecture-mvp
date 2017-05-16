package com.lev.mvpcleanarch.data.repositories;

import com.lev.mvpcleanarch.data.entity.TaskEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class TaskRepository extends BaseRepository {

    public Observable<List<TaskEntity>> getTasks() {
        return getDataSource().getTasks();
    }
}
