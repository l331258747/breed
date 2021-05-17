package com.play.breed.util.http;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by LGQ
 * Time: 2018/7/18
 * Function:
 */

public class MethodApi {

    //==============登录   start
    //登录
    public static void login(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().login(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //注册
    public static void register(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().register(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //用户找回密码
    public static void forget(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().forget(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    //验证码
    public static void verifyCode(Map<String, String> params, DisposableObserver subscriber) {
        Observable observable = HttpMethods.getInstance().getHttpService().verifyCode(getRequestBody(params)); //在HttpServer中
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    //==============登录   end

    private static RequestBody getRequestBody(Map<String, String> params) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                new JSONObject(params).toString());
        return requestBody;
    }

    private static MultipartBody.Part fileToMultipartBodyParts(File file) {
        return fileToMultipartBodyParts(file,"file");
    }
    private static MultipartBody.Part fileToMultipartBodyParts(File file,String name) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
        return part;
    }

    private static RequestBody getStringPart(String str) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), str);
        return requestBody;
    }

}
