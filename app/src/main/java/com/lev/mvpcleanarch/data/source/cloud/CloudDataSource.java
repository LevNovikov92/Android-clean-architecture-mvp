package com.lev.mvpcleanarch.data.source.cloud;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
import com.lev.mvpcleanarch.data.entity.mapper.TaskJsonMapper;
import com.lev.mvpcleanarch.data.source.DataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Singleton
public class CloudDataSource implements DataSource {

    private final CloudApi mApi;

    private final TaskJsonMapper mTaskMapper;

    @Inject
    CloudDataSource(CloudApi api, TaskJsonMapper taskMapper) {
        this.mApi = api;
        this.mTaskMapper = taskMapper;
    }

    @Override
    public Observable<List<TaskEntity>> getTasks() {
        return Observable.create(emitter -> {
            try {
                mApi.jsonGet(TaskEntity.CLOUD_PATH).subscribe(
                        json -> {
                            emitter.onNext(mTaskMapper.fromJson(json));
                            emitter.onComplete();
                        },
                        emitter::onError);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
