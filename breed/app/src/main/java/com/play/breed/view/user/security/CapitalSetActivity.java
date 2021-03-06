package com.play.breed.view.user.security;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.util.LoginUtil;
import com.play.breed.util.StringUtils;

import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CapitalSetActivity extends BaseActivity implements View.OnClickListener {
    EditText et_pwd, et_pwd2,et_verify;
    TextView tv_verify_code,btn_submit;


    @Override
    public int getLayoutId() {
        return R.layout.activity_capital_set;
    }

    @Override
    public void initView() {
        showLeftAndTitle("设置资金密码");

        et_pwd = $(R.id.et_pwd);
        et_pwd2 = $(R.id.et_pwd2);
        et_verify = $(R.id.et_verify);
        tv_verify_code = $(R.id.tv_verify_code);
        btn_submit = $(R.id.btn_submit);

        tv_verify_code.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_verify_code:
                verifyEvent();
                showShortToast("手机验证码发送成功");

                break;
            case R.id.btn_submit:
                if (!LoginUtil.verifyPassword(et_pwd.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_pwd2.getText().toString()))
                    return;
                if (!LoginUtil.verifyPasswordDouble(et_pwd.getText().toString(), et_pwd2.getText().toString()))
                    return;
                if (!LoginUtil.verifyVerify(et_verify.getText().toString()))
                    return;

                showShortToast("设置成功");
                finish();

                break;
        }
    }

    Disposable disposable;
    private void verifyEvent() {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                    tv_verify_code.setEnabled(false);//在发送数据的时候设置为不能点击
                    tv_verify_code.setTextColor(ContextCompat.getColor(context, R.color.color_66));
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        StringUtils.setHtml(tv_verify_code, String.format(getResources().getString(R.string.verify), num));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //回复原来初始状态
                        tv_verify_code.setEnabled(true);
                        tv_verify_code.setText("重新发送");
                        tv_verify_code.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

}
