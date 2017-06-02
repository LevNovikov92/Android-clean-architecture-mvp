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
public class DomainModule {

    public static final String SCHEDULER_WORKER = "WorkerScheduler";
    public static final String SCHEDULER_UI = "UiScheduler";

    @Singleton
    @Provides
    @Named(SCHEDULER_WORKER)
    Scheduler provideWorkerScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(4));
    }

    @Singleton
    @Provides
    @Named(SCHEDULER_UI)
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
