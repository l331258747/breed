package com.play.breed.view.user.security;

import android.content.Intent;
import android.view.View;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.view.address.AddressSetActivity;
import com.play.breed.view.login.ModifyPasswordActivity;

public class SecurityHomeActivity extends BaseActivity implements View.OnClickListener {

    View view_modify_login,view_modify_capital,view_authentication,view_address,view_account_number;

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_home;
    }

    @Override
    public void initView() {
        showLeftAndTitle("安全中心");

        view_modify_login = $(R.id.view_modify_login);
        view_modify_capital = $(R.id.view_modify_capital);
        view_authentication = $(R.id.view_authentication);
        view_address = $(R.id.view_address);
        view_account_number = $(R.id.view_account_number);

        view_modify_login.setOnClickListener(this);
        view_modify_capital.setOnClickListener(this);
        view_authentication.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_account_number.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_modify_login:
                startActivity(new Intent(context, ModifyPasswordActivity.class));
                break;
            case R.id.view_modify_capital:
                startActivity(new Intent(context,CapitalSetActivity.class));
                break;
            case R.id.view_authentication:
                startActivity(new Intent(context,AuthenticationActivity.class));
                break;
            case R.id.view_address:
                startActivity(new Intent(context, AddressSetActivity.class));
                break;
            case R.id.view_account_number:
                startActivity(new Intent(context,CollectionActivity.class));
                break;
        }
    }
}
