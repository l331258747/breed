package com.play.breed.view.user.security;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.user.UserAccountBean;
import com.play.breed.util.LoginUtil;

public class CollectionActivity extends BaseActivity {

    EditText et_collection;
    TextView btn_submit;


    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        showLeftAndTitle("设置收款账号");

        et_collection = $(R.id.et_collection);
        btn_submit = $(R.id.btn_submit);

    }

    @Override
    public void initData() {
        getUserAccountSuccess(new UserAccountBean());
    }

    public void getUserAccountSuccess(UserAccountBean data) {
        btn_submit.setText("完成");
        if(data == null || TextUtils.isEmpty(data.getAlipayAccount())){
            btn_submit.setBackgroundResource(R.drawable.btn_1c81e9_r40);
            btn_submit.setOnClickListener(v -> {
                if (!LoginUtil.verifyEmpty(et_collection.getText().toString(),"请输入收款账号"))
                    return;

                showShortToast("设置成功");
                finish();
            });

            et_collection.setFocusable(true);
            et_collection.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
            et_collection.setClickable(true); // user navigates with wheel and selects widget

        }else{
            et_collection.setText(data.getAlipayAccount());

            btn_submit.setBackgroundResource(R.drawable.btn_cc_r40);
            btn_submit.setOnClickListener(null);

            et_collection.setFocusable(false);
            et_collection.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            et_collection.setClickable(false); // user navigates with wheel and selects widget
        }

    }

}
