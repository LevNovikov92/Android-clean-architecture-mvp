package com.lev.mvpcleanarch.presentation.view;

import com.lev.mvpcleanarch.domain.Task;

import java.util.List;

/**
 * Author: Lev
 * Date: 17.05.2017
 */

public interface TaskListView extends View {

    void showProgress();

    void showZeroState();

    void displayTasks(List<Task> tasks);

}
