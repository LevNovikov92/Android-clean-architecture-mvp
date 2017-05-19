package com.lev.mvpcleanarch.presentation.view.fragment;

import android.support.v4.app.Fragment;

import com.lev.mvpcleanarch.presentation.presenter.Presenter;

/**
 * Author: Lev
 * Date: 18.05.2017
 */

public abstract class BaseFragment extends Fragment {

    abstract Presenter basePresenter();

    @Override
    public void onResume() {
        super.onResume();
        basePresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        basePresenter().onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        basePresenter().onDestroy();
    }
}
