package com.obabichev.technomessenger.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.obabichev.technomessenger.R;
import com.obabichev.technomessenger.cleanmvp.view.fragment.BaseFragment;
import com.obabichev.technomessenger.presenter.fragment.LoginPresenter;
import com.obabichev.technomessenger.view.activity.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.jakewharton.rxbinding.view.RxView.clicks;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class LoginScreenFragment extends BaseFragment<LoginPresenter, MainView> implements LoginScreenView {

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.registration_button)
    Button registrationButton;

    @BindView(R.id.create_account_button)
    Button createAccountButton;

    @BindView(R.id.nickname_text)
    TextView nicknameTextView;

    @BindView(R.id.nickname_input)
    EditText nicknameInput;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.login_screen;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }

    @Override
    public Observable<Void> getLoginButtonClicks() {
        return clicks(loginButton);
    }

    @Override
    public Observable<Void> getRegisterButtonClicks() {
        return clicks(registrationButton);
    }

    @Override
    public Observable<Void> getCreateAccountButtonClicks() {
        return clicks(createAccountButton);
    }

    @Override
    public void switchToCreateAccount() {
        loginButton.setVisibility(GONE);
        registrationButton.setVisibility(GONE);

        nicknameInput.setVisibility(VISIBLE);
        nicknameTextView.setVisibility(VISIBLE);
        createAccountButton.setVisibility(VISIBLE);
    }
}
