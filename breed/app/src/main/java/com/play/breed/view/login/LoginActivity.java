package com.play.breed.view.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.MySelfInfo;
import com.play.breed.bean.login.LoginBean;
import com.play.breed.mvp.login.LoginContract;
import com.play.breed.mvp.login.LoginPresenter;
import com.play.breed.util.LoginUtil;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.RegisterEvent;
import com.play.breed.view.home.HomeActivity;

import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    EditText et_account, et_password;
    TextView tv_btn, tv_register, tv_forget;

    LoginPresenter mPresenter;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        hideTitleLayout();

        et_account = $(R.id.et_account);
        et_password = $(R.id.et_password);
        tv_btn = $(R.id.tv_btn);
        tv_register = $(R.id.tv_register);
        tv_forget = $(R.id.tv_forget);

        tv_btn.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        setDebug();
    }

    public void setDebug() {
        et_account.setText("13212615202");
        et_password.setText("123456");
    }

    @Override
    public void initData() {
        mPresenter = new LoginPresenter(context, this);

        disposable = RxBus2.getInstance().toObservable(RegisterEvent.class, registerEvent -> {
            et_account.setText(registerEvent.getUserName());
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_btn:

                if (!LoginUtil.verifyPhone(et_account.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                    return;

                mPresenter.login(et_account.getText().toString(), et_password.getText().toString());

                break;
            case R.id.tv_register: {
                Intent intent = new Intent(context, RegisterActivity.class);
                intent.putExtra("mobile", et_account.getText().toString());
                startActivity(intent);
            }
            break;
            case R.id.tv_forget: {
                Intent intent = new Intent(context, ForgetActivity.class);
                intent.putExtra("mobile", et_account.getText().toString());
                startActivity(intent);
            }
            break;
        }
    }

    @Override
    public void loginSuccess(LoginBean data) {
        MySelfInfo.getInstance().setLoginData(data, et_account.getText().toString());
        finish();
        startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    public void loginFailed(String msg) {
        showShortToast(msg);
    }
}
