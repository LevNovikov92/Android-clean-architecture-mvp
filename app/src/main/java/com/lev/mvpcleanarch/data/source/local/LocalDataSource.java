package com.lev.mvpcleanarch.data.source.local;

import com.lev.mvpcleanarch.data.entity.Task;
import com.lev.mvpcleanarch.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class LocalDataSource implements DataSource {

    @Override
    public Observable<List<Task>> findAll() {
        return null;
    }
}
