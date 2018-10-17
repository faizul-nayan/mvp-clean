package com.mvpclear.arichitecture.presentation;

import com.mvpclear.arichitecture.domain.executor.Executor;
import com.mvpclear.arichitecture.domain.executor.MainThread;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */
public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
