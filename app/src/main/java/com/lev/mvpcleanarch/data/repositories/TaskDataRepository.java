package com.lev.mvpcleanarch.data.repositories;

import com.lev.mvpcleanarch.data.source.DataSourceFactory;
import com.lev.mvpcleanarch.domain.Task;
import com.lev.mvpcleanarch.domain.mapper.TaskMapper;
import com.lev.mvpcleanarch.domain.repository.TaskRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Singleton
public class TaskDataRepository extends BaseRepository implements TaskRepository {

    @Inject
    public TaskDataRepository(DataSourceFactory dataSourceFactory) {
        super(dataSourceFactory);
    }

    @Override
    public Observable<List<Task>> getTasks() {
        return getDataSource().getTasks().map(TaskMapper::mapList);
    }
}
