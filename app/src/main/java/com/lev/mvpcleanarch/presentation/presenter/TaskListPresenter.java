package com.lev.mvpcleanarch.presentation.presenter;

import com.lev.mvpcleanarch.domain.Task;
import com.lev.mvpcleanarch.domain.interactor.GetTasksInteractor;
import com.lev.mvpcleanarch.presentation.view.TaskListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Author: Lev
 * Date: 17.05.2017
 */

public class TaskListPresenter implements Presenter {

    private TaskListView mView;

    private final GetTasksInteractor mInteractor;

    @Inject
    TaskListPresenter(GetTasksInteractor interactor) {
        this.mInteractor = interactor;
    }

    public void setView(TaskListView view) {
        mView = view;
    }

    public void loadTasks() {
        mInteractor.execute(new DisposableObserver<List<Task>>() {
            @Override
            protected void onStart() {
                mView.showProgress();
            }

            @Override
            public void onNext(@NonNull List<Task> tasks) {
                if (tasks.isEmpty()) mView.showZeroState();
                else mView.displayTasks(tasks);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.showZeroState();
                e.printStackTrace();
            }

            @Override
            public void onComplete() {}
        }, null);
    }

    @Override
    public void onResume() { }

    @Override
    public void onPause() { }

    @Override
    public void onDestroy() {
        mInteractor.disposeAll();
    }

    public void onItemClick(String id) {
        mView.showMessage("Task id: " + id);
    }
}
