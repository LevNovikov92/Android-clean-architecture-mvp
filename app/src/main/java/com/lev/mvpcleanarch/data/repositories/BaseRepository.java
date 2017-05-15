package com.lev.mvpcleanarch.data.repositories;

import android.support.annotation.VisibleForTesting;

import com.lev.mvpcleanarch.data.source.DataSource;
import com.lev.mvpcleanarch.data.source.DataSourceFactory;

import javax.inject.Inject;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

abstract class BaseRepository {

    @SuppressWarnings("WeakerAccess")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    public DataSourceFactory mDataSourceFactory;

    DataSource getDataSource() {
        return mDataSourceFactory.getDataSource();
    }
}
