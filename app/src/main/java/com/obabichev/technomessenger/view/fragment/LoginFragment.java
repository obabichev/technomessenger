package com.obabichev.technomessenger.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.jakewharton.rxbinding.view.RxView.clicks;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class LoginFragment extends BaseFragment<LoginPresenter, MainView> implements LoginView {

    @BindView(R.id.login_input)
    EditText loginInput;

    @BindView(R.id.password_input)
    EditText passwordInput;

    @BindView(R.id.nickname_text)
    TextView nicknameTextView;

    @BindView(R.id.nickname_input)
    EditText nicknameInput;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.registration_button)
    Button registrationButton;

    @BindView(R.id.create_account_button)
    Button createAccountButton;

    private PublishSubject<Void> backPressEvents;

    private boolean isLoginState = true;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        backPressEvents = PublishSubject.create();

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
    public PublishSubject<Void> backPressEvents() {
        return backPressEvents;
    }

    @Override
    public void switchToCreateAccountState() {
        isLoginState = false;
        changeState(false);
    }

    @Override
    public void switchToLoginState() {
        isLoginState = true;
        changeState(true);
    }

    private void changeState(boolean isLogin) {
        loginButton.setVisibility(isLogin ? VISIBLE : GONE);
        registrationButton.setVisibility(isLogin ? VISIBLE : GONE);

        nicknameInput.setVisibility(isLogin ? GONE : VISIBLE);
        nicknameTextView.setVisibility(isLogin ? GONE : VISIBLE);
        createAccountButton.setVisibility(isLogin ? GONE : VISIBLE);
    }

    @Override
    public String getLogin() {
        return loginInput.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordInput.getText().toString();
    }

    @Override
    public String getNickname() {
        return nicknameInput.getText().toString();
    }

    @Override
    public boolean isLoginState() {
        return isLoginState;
    }


}
