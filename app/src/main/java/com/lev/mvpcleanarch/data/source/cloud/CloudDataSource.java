package com.lev.mvpcleanarch.data.source.cloud;

import android.support.annotation.VisibleForTesting;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
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
    public Observable<List<TaskEntity>> getTasks() {
        return Observable.create(emitter -> {
            try {
                api.jsonGet(TaskEntity.CLOUD_PATH).subscribe(
                        json -> {
                            emitter.onNext(taskMapper.fromJson(json));
                            emitter.onComplete();
                        },
                        emitter::onError);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
