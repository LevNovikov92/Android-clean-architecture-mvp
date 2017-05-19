package com.lev.mvpcleanarch.domain.repository;

import com.lev.mvpcleanarch.domain.Task;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 18.05.2017
 */

public interface TaskRepository {
    Observable<List<Task>> getTasks();
}
