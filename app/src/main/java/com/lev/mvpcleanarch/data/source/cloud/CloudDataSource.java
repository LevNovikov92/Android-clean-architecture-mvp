package com.lev.mvpcleanarch.data.source.cloud;

import android.support.annotation.VisibleForTesting;

import com.lev.mvpcleanarch.data.entity.Task;
import com.lev.mvpcleanarch.data.entity.mapper.TaskMapper;
import com.lev.mvpcleanarch.data.source.DataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class CloudDataSource implements DataSource {

    @SuppressWarnings("WeakerAccess")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    public CloudApi api;

    @SuppressWarnings("WeakerAccess")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    public TaskMapper taskMapper;

    @Override
    public Observable<List<Task>> getTasks() {
        return Observable.create(emitter -> {
            try {
                final String json = api.jsonGet(Task.CLOUD_PATH);
                emitter.onNext(taskMapper.fromJson(json));
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
