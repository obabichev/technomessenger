package com.obabichev.technomessenger.cleanmvp.presenter.fragment;

import com.obabichev.technomessenger.cleanmvp.presenter.Presenter;
import com.obabichev.technomessenger.cleanmvp.view.fragment.FragmentView;

public interface FragmentPresenter<T extends FragmentView> extends Presenter<T, FragmentLifecycle> {

}