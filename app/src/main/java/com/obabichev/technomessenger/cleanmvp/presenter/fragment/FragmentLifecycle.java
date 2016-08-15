package com.obabichev.technomessenger.cleanmvp.presenter.fragment;

import com.obabichev.technomessenger.cleanmvp.presenter.Lifecycle;

public interface FragmentLifecycle extends Lifecycle {

    void onAttach();

    void onCreate();

    void onCreateView();

    void onActivityCreated();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onDetach();
}
