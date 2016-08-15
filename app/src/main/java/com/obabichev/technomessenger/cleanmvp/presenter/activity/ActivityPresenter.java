package com.obabichev.technomessenger.cleanmvp.presenter.activity;

import com.obabichev.technomessenger.cleanmvp.presenter.Presenter;
import com.obabichev.technomessenger.cleanmvp.view.activity.ActivityView;

public interface ActivityPresenter<V extends ActivityView> extends Presenter<V, ActivityLifecycle> {

}
