package com.lev.mvpcleanarch.data.source;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lev.mvpcleanarch.data.source.cloud.CloudDataSource;
import com.lev.mvpcleanarch.data.source.local.LocalDataSource;

import javax.inject.Inject;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class DataSourceFactory {

    public Context context;

    private CloudDataSource mCloudDataSource;

    private LocalDataSource mLocalDataSource;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public DataSourceFactory(
            Context context,
            CloudDataSource cloudDataSource,
            LocalDataSource localDataSource) {
        this.context = context;
        this.mCloudDataSource = cloudDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public DataSource getDataSource() {
        if (isInternetConnected()) return mCloudDataSource;
        else return mLocalDataSource;
    }

    private boolean isInternetConnected() {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
