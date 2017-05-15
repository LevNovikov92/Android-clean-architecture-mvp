package com.lev.mvpcleanarch.data.repositories;

import android.support.annotation.VisibleForTesting;

import com.lev.mvpcleanarch.data.source.DataSource;
import com.lev.mvpcleanarch.data.source.DataSourceFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

abstract class BaseRepository<T> {

    private final Class<T> mClass;

    BaseRepository(Class<T> clazz) {
        mClass = clazz;
    }

    @SuppressWarnings("WeakerAccess")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    public DataSourceFactory mDataSourceFactory;

    private DataSource getDataSource() {
        return mDataSourceFactory.getDataSource();
    }

    Observable<List<T>> finadAll() {
        return getDataSource().findAll(mClass);
    }
}
