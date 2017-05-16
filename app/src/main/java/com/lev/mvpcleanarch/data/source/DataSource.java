package com.lev.mvpcleanarch.data.source;

import com.lev.mvpcleanarch.data.entity.TaskEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public interface DataSource {

    Observable<List<TaskEntity>>  getTasks();
}
