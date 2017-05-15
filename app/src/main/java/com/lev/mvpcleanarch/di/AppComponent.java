package com.lev.mvpcleanarch.di;

import com.lev.mvpcleanarch.data.repositories.TaskRepository;
import com.lev.mvpcleanarch.data.source.DataSourceFactory;
import com.lev.mvpcleanarch.data.source.cloud.CloudApiImpl;
import com.lev.mvpcleanarch.data.source.cloud.CloudDataSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface AppComponent {

    void inject(CloudApiImpl api);
    
    void inject(DataSourceFactory factory);

    void inject(TaskRepository repository);

    void inject(CloudDataSource dataSource);
}
