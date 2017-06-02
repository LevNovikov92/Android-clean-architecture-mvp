package com.lev.mvpcleanarch.di;

import android.content.Context;

import com.lev.mvpcleanarch.domain.repository.TaskRepository;
import com.lev.mvpcleanarch.presentation.view.fragment.TaskListFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Singleton
@Component(modules = { AppModule.class, DataModule.class, DomainModule.class })
public interface AppComponent {

    void inject(TaskListFragment taskListFragment);

    Context provideContext();

    @Named(DomainModule.SCHEDULER_WORKER)
    Scheduler provideWorkerScheduler();

    @Named(DomainModule.SCHEDULER_UI)
    Scheduler provideUiScheduler();

    TaskRepository provideTaskRepository();
}
