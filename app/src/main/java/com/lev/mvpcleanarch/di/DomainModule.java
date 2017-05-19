package com.lev.mvpcleanarch.di;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Lev
 * Date: 17.05.2017
 */

@Module
class DomainModule {

    @Singleton
    @Provides
    @Named("WorkerScheduler")
    Scheduler provideWorkerScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(4));
    }

    @Singleton
    @Provides
    @Named("UiScheduler")
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
