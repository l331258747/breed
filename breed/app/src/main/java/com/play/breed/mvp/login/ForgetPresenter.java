package com.play.breed.mvp.login;

import android.content.Context;

import com.play.breed.bean.EmptyModel;
import com.play.breed.util.http.MethodApi;
import com.play.breed.util.http.OnSuccessAndFaultSub;
import com.play.breed.util.http.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class ForgetPresenter implements ForgetContract.Presenter{

    ForgetContract.View iView;
    Context context;

    public ForgetPresenter(Context context, ForgetContract.View view) {
        this.iView = view;
        this.context = context;
    }

    @Override
    public void forget(String mobile, String password, String validCode) {
        ResponseCallback listener = new ResponseCallback<EmptyModel>() {
            @Override
            public void onSuccess(EmptyModel data) {
                iView.forgetSuccess(data);
            }

            @Override
            public void onFault(String errorMsg) {
                iView.forgetFailed(errorMsg);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("validCode",validCode);
        params.put("mobile",mobile);
//        params.put("password", MD5Utils.MD5(password));
        params.put("password", password);

        MethodApi.forget(params, new OnSuccessAndFaultSub(listener, context));
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
