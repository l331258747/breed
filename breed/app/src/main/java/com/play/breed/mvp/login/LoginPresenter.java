package com.play.breed.mvp.login;

import android.content.Context;

import com.play.breed.bean.login.LoginBean;
import com.play.breed.util.http.MethodApi;
import com.play.breed.util.http.OnSuccessAndFaultSub;
import com.play.breed.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class LoginPresenter implements LoginContract.Presenter{

    LoginContract.View iView;
    Context context;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void login(String username, String password) {
        ResponseCallback listener = new ResponseCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean data) {
                iView.loginSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.loginFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("username",username);
        params.put("password", password);
//        params.put("password", MD5Utils.MD5(password));

        MethodApi.login(params, new OnSuccessAndFaultSub(listener, context));
    }

}
