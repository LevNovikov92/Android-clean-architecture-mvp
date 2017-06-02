package com.lev.mvpcleanarch.domain.interactor;

import com.lev.mvpcleanarch.di.DomainModule;
import com.lev.mvpcleanarch.domain.Task;
import com.lev.mvpcleanarch.domain.repository.TaskRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Author: Lev
 * Date: 16.05.2017
 */

public class GetTasksInteractor extends Interactor<List<Task>, Void> {

    private final TaskRepository mTaskRepository;

    @Inject
    public GetTasksInteractor(@Named(DomainModule.SCHEDULER_WORKER) Scheduler workerScheduler,
                              @Named(DomainModule.SCHEDULER_UI) Scheduler uiScheduler, TaskRepository taskRepository) {
        super(workerScheduler, uiScheduler);
        mTaskRepository = taskRepository;
    }

    @Override
    Observable<List<Task>> performUseCase(Void params) {
        return mTaskRepository.getTasks();
    }
}
