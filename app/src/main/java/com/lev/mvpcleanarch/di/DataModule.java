package com.lev.mvpcleanarch.di;

import android.content.Context;

import com.lev.mvpcleanarch.App;
import com.lev.mvpcleanarch.data.entity.mapper.TaskMapper;
import com.lev.mvpcleanarch.data.repositories.TaskRepository;
import com.lev.mvpcleanarch.data.source.cloud.CloudApi;
import com.lev.mvpcleanarch.data.source.cloud.CloudApiImpl;
import com.lev.mvpcleanarch.data.source.cloud.CloudDataSource;
import com.lev.mvpcleanarch.data.source.local.DataBaseHelper;
import com.lev.mvpcleanarch.data.source.local.DbOpenHelper;
import com.lev.mvpcleanarch.data.source.local.LocalDataSource;

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

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    CloudApi provideApi() {
        final CloudApiImpl api = new CloudApiImpl("http://localhost/");
        App.getComponent().inject(api);
        return api;
    }

    @Singleton
    @Provides
    CloudDataSource provideCloudDataSource() {
        final CloudDataSource dataSource = new CloudDataSource();
        App.getComponent().inject(dataSource);
        return dataSource;
    }

    @Singleton
    @Provides
    LocalDataSource provideLocalDataSource() {
        final LocalDataSource source = new LocalDataSource();
        App.getComponent().inject(source);
        return source;
    }

    @Singleton
    @Provides
    TaskRepository provideTaskRepository() {
        final TaskRepository repository = new TaskRepository();
        App.getComponent().inject(repository);
        return repository;
    }

    @Singleton
    @Provides
    TaskMapper provideTaskMapper() {
        return new TaskMapper();
    }

    @Singleton
    @Provides
    DataBaseHelper provideDbHelper() {
        return mDBOpenHelper;
    }
}
