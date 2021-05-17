package com.play.breed.view.login;

import android.widget.EditText;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.util.LoginUtil;

public class ModifyPasswordActivity extends BaseActivity {

    EditText et_old_password,et_password,et_password2;
    TextView tv_btn;


    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    public void initView() {
        showLeftAndTitle("修改登录密码");

        et_old_password = $(R.id.et_old_password);
        et_password = $(R.id.et_password);
        et_password2 = $(R.id.et_password2);
        tv_btn = $(R.id.tv_btn);

        tv_btn.setOnClickListener(v -> {
            if (!LoginUtil.verifyPassword(et_old_password.getText().toString()))
                return;
            if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                return;
            if (!LoginUtil.verifyPassword(et_password2.getText().toString()))
                return;
            if (!LoginUtil.verifyPasswordDouble(et_password.getText().toString(), et_password2.getText().toString()))
                return;

            showShortToast("设置成功");
            finish();
        });
    }

    @Override
    public void initData() {

    }
}
