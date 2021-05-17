package com.play.breed.mvp.login;


import com.play.breed.bean.EmptyModel;

public interface ForgetContract {

    interface Presenter {
        void forget(String mobile,String password,String validCode);
        void verifyCode(String username);
    }

    interface View {
        void forgetSuccess(EmptyModel data);
        void forgetFailed(String msg);

        void verifyCodeSuccess(EmptyModel data);
        void verifyCodeFailed(String msg);

    }
}
