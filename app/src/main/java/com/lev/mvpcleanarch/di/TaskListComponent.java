package com.lev.mvpcleanarch.di;

import com.lev.mvpcleanarch.di.annotation.ActivityScope;
import com.lev.mvpcleanarch.presentation.view.fragment.TaskListFragment;

import dagger.Component;

/**
 * Author: Lev
 * Date: 02.06.2017
 */
@ActivityScope
@Component(dependencies = { AppComponent.class }, modules = { ActivityModule.class, TaskListModule.class })
public interface TaskListComponent extends ActivityComponent {

    void inject(TaskListFragment fragment);
}
