package com.play.breed.view.user.security;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.util.LoginUtil;
import com.play.breed.util.pickerView.PickerCityHelp;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.RegionSelEvent;
import com.play.breed.view.address.ListRegionActivity;


import io.reactivex.disposables.Disposable;

public class AuthenticationActivity extends BaseActivity {

    EditText et_real_name, et_code;
    View view_address;
    TextView tv_address, btn_submit;

    PickerCityHelp mPickerCityHelp;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initView() {
        showLeftAndTitle("实名认证");

        et_real_name = $(R.id.et_real_name);
        et_code = $(R.id.et_code);
        view_address = $(R.id.view_address);
        tv_address = $(R.id.tv_address);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            if (!LoginUtil.verifyName(et_real_name.getText().toString()))
                return;
            if (!LoginUtil.verifyID(et_code.getText().toString()))
                return;
            if (!LoginUtil.verifyEmpty(tv_address.getText().toString(), "请选择地区"))
                return;

        });

        view_address.setOnClickListener(v -> {
            startActivity(new Intent(context, ListRegionActivity.class));
        });
    }

    @Override
    public void initData() {
        inType = 0;
        mPickerCityHelp = new PickerCityHelp(context);
        mPickerCityHelp.initData();

        disposable = RxBus2.getInstance().toObservable(RegionSelEvent.class, regionSelEvent -> {
            tv_address.setText(regionSelEvent.getAddressDes());
            locationCode = regionSelEvent.getpCode();
        });

    }
    String locationCode;
    int inType;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
