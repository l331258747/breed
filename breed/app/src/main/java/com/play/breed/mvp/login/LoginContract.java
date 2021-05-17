package com.play.breed.mvp.login;


import com.play.breed.bean.login.LoginBean;

public interface LoginContract {

    interface Presenter {
        void login(String username,String password);
    }

    interface View {
        void loginSuccess(LoginBean data);
        void loginFailed(String msg);

    }
}
