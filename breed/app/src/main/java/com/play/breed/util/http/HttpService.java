package com.play.breed.util.http;

import com.play.breed.bean.BaseResponse;
import com.play.breed.bean.EmptyModel;
import com.play.breed.bean.login.LoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LGQ
 * Time: 2018/7/17
 * Function: 请求
 */

public interface HttpService {

    //==============登录   start
    //登录
    @POST("/sys/login")
    Observable<BaseResponse<LoginBean>> login(
            @Body RequestBody body
    );

    //注册
    @POST("/api/v1/sys/user/register")
    Observable<BaseResponse<EmptyModel>> register(
            @Body RequestBody body
    );

    //用户找回密码
    @POST("/api/v1/sys/user/resetPasswordForApp")
    Observable<BaseResponse<EmptyModel>> forget(
            @Body RequestBody body
    );

    //验证码
    @POST("/sys/sendCode")
    Observable<BaseResponse<EmptyModel>> verifyCode(
            @Body RequestBody body
    );
    //==============登录   end

}
