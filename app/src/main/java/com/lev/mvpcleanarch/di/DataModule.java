package com.lev.mvpcleanarch.di;

import com.lev.mvpcleanarch.Application;
import com.lev.mvpcleanarch.data.repositories.TaskRepository;
import com.lev.mvpcleanarch.data.source.cloud.Api;
import com.lev.mvpcleanarch.data.source.cloud.ApiImpl;
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

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    Api provideApi() {
        final ApiImpl api = new ApiImpl();
        Application.getComponent().inject(api);
        return api;
    }

    @Singleton
    @Provides
    CloudDataSource provideCloudDataSource() {
        return new CloudDataSource();
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
}
