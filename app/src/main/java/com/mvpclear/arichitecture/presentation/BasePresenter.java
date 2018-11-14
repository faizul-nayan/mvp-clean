package com.mvpclear.arichitecture.presentation;


/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */


public interface BasePresenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view'interactors
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view'interactors
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view'interactors
     * (Activity or Fragment) onStop() method.
     */
    void stop();

    /**
     * Method that control the lifecycle of the view. It should be called in the view'interactors
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();


    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    void onError(String message);
}
