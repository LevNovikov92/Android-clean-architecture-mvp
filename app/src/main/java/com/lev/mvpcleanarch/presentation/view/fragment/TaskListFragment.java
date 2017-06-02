package com.lev.mvpcleanarch.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lev.mvpcleanarch.R;
import com.lev.mvpcleanarch.domain.Task;
import com.lev.mvpcleanarch.presentation.presenter.Presenter;
import com.lev.mvpcleanarch.presentation.presenter.TaskListPresenter;
import com.lev.mvpcleanarch.presentation.view.TaskListView;
import com.lev.mvpcleanarch.presentation.view.activity.MainActivity;
import com.lev.mvpcleanarch.presentation.view.adapter.TaskListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Lev
 * Date: 17.05.2017
 */

public class TaskListFragment extends BaseFragment implements TaskListView {

    @Inject
    public TaskListPresenter presenter;

    @BindView(R.id.recycler_view) public RecyclerView recyclerView;
    @BindView(R.id.progress_bar) public View progressBar;
    @BindView(R.id.zero_state) public View zeroState;

    private TaskListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDI();
        mAdapter = new TaskListAdapter(getContext(), id -> {
            if (presenter != null) presenter.onItemClick(id);
        });
        setHasOptionsMenu(true);
    }

    private void initializeDI() {
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.task_list_fragment, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.loadTasks();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        zeroState.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showZeroState() {
        progressBar.setVisibility(View.GONE);
        zeroState.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void displayTasks(List<Task> tasks) {
        mAdapter.setData(tasks);
        progressBar.setVisibility(View.GONE);
        zeroState.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    Presenter basePresenter() {
        return presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_update) presenter.loadTasks();
        return true;
    }
}
