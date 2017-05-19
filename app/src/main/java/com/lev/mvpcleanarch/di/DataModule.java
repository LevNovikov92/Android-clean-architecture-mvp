package com.lev.mvpcleanarch.di;

import android.content.Context;

import com.lev.mvpcleanarch.data.repositories.TaskDataRepository;
import com.lev.mvpcleanarch.data.source.DataSourceFactory;
import com.lev.mvpcleanarch.data.source.cloud.CloudApi;
import com.lev.mvpcleanarch.data.source.cloud.CloudApiImpl;
import com.lev.mvpcleanarch.data.source.local.DataBaseHelper;
import com.lev.mvpcleanarch.data.source.local.DbOpenHelper;
import com.lev.mvpcleanarch.domain.repository.TaskRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Module
public class DataModule {

    private final DbOpenHelper mDBOpenHelper;

    public DataModule(Context context) {
        mDBOpenHelper = new DbOpenHelper(context);
    }

    @Provides
    @Named("serverUrl")
    String provideServerUrl() {
        return "http://localhost/";
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    CloudApi provideApi(@Named("serverUrl") String serverUrl, OkHttpClient client) {
        return new CloudApiImpl(serverUrl, client);
    }

    @Singleton
    @Provides
    DataBaseHelper provideDbHelper() {
        return mDBOpenHelper;
    }

    @Singleton
    @Provides
    TaskRepository provideTaskRepository(DataSourceFactory factory) {
        return new TaskDataRepository(factory);
    }
}
