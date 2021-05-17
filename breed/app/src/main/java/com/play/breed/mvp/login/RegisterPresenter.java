package com.play.breed.mvp.login;

import android.content.Context;

import com.play.breed.bean.EmptyModel;
import com.play.breed.util.http.MethodApi;
import com.play.breed.util.http.OnSuccessAndFaultSub;
import com.play.breed.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class RegisterPresenter implements RegisterContract.Presenter{

    RegisterContract.View iView;
    Context context;

    public RegisterPresenter(Context context, RegisterContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void register(String username, String password, String myCode, String validCode) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.registerSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.registerFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("myCode",myCode);
        params.put("validCode",validCode);
        params.put("username",username);
//        params.put("password", MD5Utils.MD5(password));
        params.put("password", password);

        MethodApi.register(params, new OnSuccessAndFaultSub(listener, context));
    }

    @Override
    public void verifyCode(String username) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.verifyCodeSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.verifyCodeFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("mobile",username);

        MethodApi.verifyCode(params, new OnSuccessAndFaultSub(listener, context));
    }
}
