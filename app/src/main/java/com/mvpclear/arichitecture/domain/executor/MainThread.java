package com.mvpclear.arichitecture.domain.executor;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */
public interface MainThread {

    /**
     * Make runnable operation run in the main thread.
     *
     * @param runnable The runnable to run.
     */
    void post(final Runnable runnable);
}
