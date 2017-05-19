package com.lev.mvpcleanarch.domain.interactor;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Author: Lev
 * Date: 16.05.2017
 */

public abstract class Interactor<T, P> {

    private final Scheduler mWorkerScheduler;
    private final Scheduler mUiScheduler;
    private final CompositeDisposable mCompositeDisposable;

    Interactor(Scheduler workerScheduler, Scheduler uiScheduler) {
        this.mWorkerScheduler = workerScheduler;
        this.mUiScheduler = uiScheduler;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    abstract Observable<T> performUseCase(@Nullable P params);

    public void execute(DisposableObserver<T> observer, @Nullable P params) {
        final Observable<T> observable = performUseCase(params)
                .subscribeOn(mWorkerScheduler)
                .observeOn(mUiScheduler);
        addDisposable(observable.subscribeWith(observer));
    }

    private void addDisposable(Disposable observer) {
        mCompositeDisposable.add(observer);
    }

    public void disposeAll() {
        mCompositeDisposable.dispose();
    }
}
