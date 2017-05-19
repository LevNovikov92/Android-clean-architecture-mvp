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

    private final Context mContext;

    private final CloudDataSource mCloudDataSource;

    private final LocalDataSource mLocalDataSource;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public DataSourceFactory(
            Context context,
            CloudDataSource cloudDataSource,
            LocalDataSource localDataSource) {
        this.mContext = context;
        this.mCloudDataSource = cloudDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public DataSource getDataSource() {
        if (isInternetConnected()) return mCloudDataSource;
        else return mLocalDataSource;
    }

    private boolean isInternetConnected() {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting() && false; //TODO force to use local storage
    }
}
