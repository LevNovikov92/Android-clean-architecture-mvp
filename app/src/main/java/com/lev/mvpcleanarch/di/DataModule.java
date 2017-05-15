package com.lev.mvpcleanarch.di;

import com.lev.mvpcleanarch.Application;
import com.lev.mvpcleanarch.data.entity.mapper.TaskMapper;
import com.lev.mvpcleanarch.data.repositories.TaskRepository;
import com.lev.mvpcleanarch.data.source.cloud.CloudApi;
import com.lev.mvpcleanarch.data.source.cloud.CloudApiImpl;
import com.lev.mvpcleanarch.data.source.cloud.CloudDataSource;
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

    private final String BASE_URL = "http://localhost/";

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    CloudApi provideApi() {
        final CloudApiImpl api = new CloudApiImpl(BASE_URL);
        Application.getComponent().inject(api);
        return api;
    }

    @Singleton
    @Provides
    CloudDataSource provideCloudDataSource() {
        final CloudDataSource dataSource = new CloudDataSource();
        Application.getComponent().inject(dataSource);
        return dataSource;
    }

    @Singleton
    @Provides
    LocalDataSource provideLocalDataSource() {
        return new LocalDataSource();
    }

    @Singleton
    @Provides
    TaskRepository provideTaskRepository() {
        final TaskRepository repository = new TaskRepository();
        Application.getComponent().inject(repository);
        return repository;
    }

    @Singleton
    @Provides
    TaskMapper provideTaskMapper() {
        return new TaskMapper();
    }
}
