package com.lev.mvpcleanarch.data.repositories;

import com.lev.mvpcleanarch.data.source.DataSource;
import com.lev.mvpcleanarch.data.source.DataSourceFactory;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

abstract class BaseRepository {

    private final DataSourceFactory mDataSourceFactory;

    BaseRepository(DataSourceFactory mDataSourceFactory) {
        this.mDataSourceFactory = mDataSourceFactory;
    }

    DataSource getDataSource() {
        return mDataSourceFactory.getDataSource();
    }
}
