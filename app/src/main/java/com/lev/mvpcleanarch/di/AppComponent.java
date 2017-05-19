package com.lev.mvpcleanarch.di;

import com.lev.mvpcleanarch.presentation.view.fragment.TaskListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class, DomainModule.class, PresentationModule.class })
public interface AppComponent {

    void inject(TaskListFragment taskListFragment);
}
