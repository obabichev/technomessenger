package com.obabichev.technomessenger.cleanmvp.view.fragment;

import android.os.Bundle;

import com.obabichev.technomessenger.cleanmvp.view.activity.ActivityView;

public interface FragmentView<V extends ActivityView> {

    <R> void switchToFragment(Class<R> fragmentClass, Bundle args, boolean addToBackStack);

    Bundle getArguments();

    V getActivityView();

    FragmentView<V> getContainerFragmentView();
}
