package com.play.breed.view.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.MySelfInfo;
import com.play.breed.bean.login.LoginBean;
import com.play.breed.util.LoginUtil;
import com.play.breed.view.home.HomeActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText et_account,et_password;
    TextView tv_btn;

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

        tv_btn.setOnClickListener(this);

    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_btn:

                if (!LoginUtil.verifyPhone(et_account.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                    return;

                 LoginBean data = new LoginBean();
                data.setToken("token");
                data.setUserId("123");

                MySelfInfo.getInstance().setLoginData(data, et_account.getText().toString());
                finish();
                startActivity(new Intent(context, HomeActivity.class));
                break;
        }
    }
}
