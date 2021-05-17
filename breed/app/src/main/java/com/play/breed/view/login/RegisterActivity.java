package com.play.breed.view.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.EmptyModel;
import com.play.breed.mvp.login.RegisterContract;
import com.play.breed.mvp.login.RegisterPresenter;
import com.play.breed.util.LoginUtil;
import com.play.breed.util.StringUtils;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.RegisterEvent;
import com.play.breed.view.web.WebTextActivity;

import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterContract.View {

    EditText et_account,et_password,et_verify,et_recommend;
    TextView tv_verify_code,tv_agreement,tv_btn;
    ImageView iv_check;

    boolean isCheck = false;

    RegisterPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        showLeftAndTitle("注册");

        et_account = $(R.id.et_account);
        et_password = $(R.id.et_password);
        et_verify = $(R.id.et_verify);
        et_recommend = $(R.id.et_recommend);
        tv_verify_code = $(R.id.tv_verify_code);
        tv_agreement = $(R.id.tv_agreement);
        tv_btn = $(R.id.tv_btn);
        iv_check = $(R.id.iv_check);

        tv_verify_code.setOnClickListener(this);
        tv_agreement.setOnClickListener(this);
        tv_btn.setOnClickListener(this);
        iv_check.setOnClickListener(this);

        String mobile = intent.getStringExtra("mobile");

        if(!TextUtils.isEmpty(mobile))
            et_account.setText(mobile);
    }

    @Override
    public void initData() {
        mPresenter = new RegisterPresenter(context,this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_verify_code:
                if (!LoginUtil.verifyPhone(et_account.getText().toString()))
                    return;

                mPresenter.verifyCode(et_account.getText().toString());
                verifyEvent();

                break;
            case R.id.tv_agreement:
                intent = new Intent(context, WebTextActivity.class);
                intent.putExtra("web_text", "服务协议");
                startActivity(intent);
                break;
            case R.id.tv_btn:
                if (!LoginUtil.verifyPhone(et_account.getText().toString()))
                    return;
                if (!LoginUtil.verifyVerify(et_verify.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                    return;
//                if (!LoginUtil.verifyEmpty(et_recommend.getText().toString(), "请输入邀请码"))
//                    return;
                if(!isCheck){
                    showShortToast("请勾选隐私协议");
                    return;
                }

                mPresenter.register(et_account.getText().toString(),
                        et_password.getText().toString(),
                        et_verify.getText().toString(),
                        et_recommend.getText().toString());

                break;
            case R.id.iv_check:
                isCheck = !isCheck;
                iv_check.setImageResource(isCheck ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
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
    public void registerSuccess(EmptyModel data) {
        RxBus2.getInstance().post(new RegisterEvent(et_account.getText().toString()));
        finish();
    }

    @Override
    public void registerFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void verifyCodeSuccess(EmptyModel data) {
        showShortToast("手机验证码发送成功");
        et_verify.setText("123456");
    }

    @Override
    public void verifyCodeFailed(String msg) {
        showShortToast(msg);
    }
}
