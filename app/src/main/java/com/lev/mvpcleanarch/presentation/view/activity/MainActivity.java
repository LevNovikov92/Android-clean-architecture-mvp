package com.lev.mvpcleanarch.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lev.mvpcleanarch.App;
import com.lev.mvpcleanarch.R;
import com.lev.mvpcleanarch.di.ActivityModule;
import com.lev.mvpcleanarch.di.DaggerTaskListComponent;
import com.lev.mvpcleanarch.di.TaskListComponent;
import com.lev.mvpcleanarch.presentation.view.fragment.TaskListFragment;

public class MainActivity extends AppCompatActivity {

    private TaskListComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeInjection();
        showFragment(new TaskListFragment());
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    void initializeInjection() {
        mComponent = DaggerTaskListComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public TaskListComponent getComponent() {
        return mComponent;
    }
}
