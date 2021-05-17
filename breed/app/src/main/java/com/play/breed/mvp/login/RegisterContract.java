package com.play.breed.mvp.login;


import com.play.breed.bean.EmptyModel;

public interface RegisterContract {

    interface Presenter {
        void register(String username,String password,String myCode,String validCode);
        void verifyCode(String username);
    }

    interface View {
        void registerSuccess(EmptyModel data);
        void registerFailed(String msg);

        void verifyCodeSuccess(EmptyModel data);
        void verifyCodeFailed(String msg);

    }
}
