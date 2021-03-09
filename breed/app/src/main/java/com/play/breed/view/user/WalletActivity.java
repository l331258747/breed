package com.play.breed.view.user;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;

public class WalletActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public void initView() {
        showLeftAndTitle("钱包");
    }

    @Override
    public void initData() {

    }
}
