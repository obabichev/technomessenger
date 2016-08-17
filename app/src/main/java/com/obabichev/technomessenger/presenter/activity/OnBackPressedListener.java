package com.obabichev.technomessenger.presenter.activity;

/**
 * Created by olegchuikin on 17/08/16.
 */

public interface OnBackPressedListener {

    /**
     * @return true if back press caught or false if back press should be thrown to the app
     */
    boolean onBackPressed();

}
