package com.mvpclear.arichitecture.domain.executor;


import com.mvpclear.arichitecture.domain.interactors.base.AbstractInteractor;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */
public interface Executor {

    /**
     * This method should call the interactor'interactors run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
