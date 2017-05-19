package com.lev.mvpcleanarch.presentation.presenter;

import com.lev.mvpcleanarch.domain.Task;
import com.lev.mvpcleanarch.domain.interactor.GetTasksInteractor;
import com.lev.mvpcleanarch.domain.repository.TaskRepository;
import com.lev.mvpcleanarch.internal.utils.TestUtils;
import com.lev.mvpcleanarch.presentation.view.TaskListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;

/**
 * Author: Lev
 * Date: 18.05.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskListPresenterTest {

    private TaskListView view;
    private TaskRepository repository;
    private final Scheduler mScheduler = Schedulers.io();
    private TaskListPresenter mPresenter;

    @Before
    public void setUp() {
        view = Mockito.mock(TaskListView.class);
        repository = Mockito.mock(TaskRepository.class);
        final GetTasksInteractor interactor = new GetTasksInteractor(mScheduler, mScheduler, repository);
        mPresenter = new TaskListPresenter(interactor);
    }

    @Test
    public void loadTasks_withData() throws Exception {
        mPresenter.setView(view);

        final List<Task> tasks = new ArrayList<>();
        tasks.add(TestUtils.getTask("1", "title1", "desc1", true));
        tasks.add(TestUtils.getTask("2", "title2", "desc2", true));
        Mockito.when(repository.getTasks()).thenReturn(Observable.just(tasks));

        mPresenter.loadTasks();

        verify(view).showProgress();
        verify(view).displayTasks(anyList());
    }
}