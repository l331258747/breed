package com.play.breed.view.order;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.order.OrderDetailBean;
import com.play.breed.dialog.TextDialog;
import com.play.breed.util.StringUtils;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.AddressEditEvent;
import com.play.breed.view.address.AddressSetActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {


    TextView tv_status, tv_address_name, tv_address_phone, tv_address_detail, tv_address_edit, tv_name, tv_num, tv_price, tv_price_goods,
            tv_freight, tv_price_all, tv_word_NO, tv_word_NO_copy, tv_word_createTime, tv_word_payTime, tv_word_deliverTime, tv_word_receivingTime,
            btn_cancel, btn_pay, btn_receive, tv_surplus_time;

    ImageView iv_img;

    LinearLayout ll_btn;

    String orderId;

    OrderDetailBean data;

    Disposable disposableDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        showLeftAndTitle("订单详情");

        orderId = intent.getStringExtra("orderId");

        tv_status = $(R.id.tv_status);
        tv_address_name = $(R.id.tv_address_name);
        tv_address_phone = $(R.id.tv_address_phone);
        tv_address_detail = $(R.id.tv_address_detail);
        tv_address_edit = $(R.id.tv_address_edit);
        tv_name = $(R.id.tv_name);
        tv_num = $(R.id.tv_num);
        tv_price = $(R.id.tv_price);
        tv_price_goods = $(R.id.tv_price_goods);
        tv_surplus_time = $(R.id.tv_surplus_time);

        tv_freight = $(R.id.tv_freight);
        tv_price_all = $(R.id.tv_price_all);
        tv_word_NO = $(R.id.tv_word_NO);
        tv_word_NO_copy = $(R.id.tv_word_NO_copy);
        tv_word_createTime = $(R.id.tv_word_createTime);
        tv_word_payTime = $(R.id.tv_word_payTime);
        tv_word_deliverTime = $(R.id.tv_word_deliverTime);
        tv_word_receivingTime = $(R.id.tv_word_receivingTime);
        btn_cancel = $(R.id.btn_cancel);
        btn_receive = $(R.id.btn_receive);
        btn_pay = $(R.id.btn_pay);

        iv_img = $(R.id.iv_img);
        ll_btn = $(R.id.ll_btn);

        btn_cancel.setOnClickListener(this);
        btn_pay.setOnClickListener(this);
        tv_address_edit.setOnClickListener(this);
        tv_word_NO_copy.setOnClickListener(this);
        btn_receive.setOnClickListener(this);

    }

    @Override
    public void initData() {
        disposableDetail = RxBus2.getInstance().toObservable(AddressEditEvent.class, addressEditEvent ->
                {
                    //                  mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(), orderId)
                }

        );


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                new TextDialog(context).setContent("是否确认取消订单？").setSubmitListener(v1 -> {
//                    mPresenter.cancelOrder(MySelfInfo.getInstance().getUserId(),orderId);
                }).show();
                break;
            case R.id.btn_pay:
                new TextDialog(context).setContent("是否确认取消订单？").setSubmitListener(v1 -> {
//                    mInfoPresenter.mine(MySelfInfo.getInstance().getUserId(),true);
                }).show();
                break;
            case R.id.btn_receive:
                new TextDialog(context).setContent("是否确认收货？").setSubmitListener(v1 -> {
//                    mPresenter.receiveOrder(MySelfInfo.getInstance().getUserId(),orderId);
                }).show();
                break;
            case R.id.tv_address_edit:
                startActivity(new Intent(context, AddressSetActivity.class));
                break;
            case R.id.tv_word_NO_copy:
                ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText("12312");
                showShortToast("复制订单编号成功");
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposableDetail != null && !disposableDetail.isDisposed())
            disposableDetail.dispose();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    Disposable disposable;
    public void verifyEvent(int count,String str) {
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        tv_surplus_time.setText("剩"+ StringUtils.getHour(num) + str);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
//                        mPresenter.getOrderDetail(MySelfInfo.getInstance().getUserId(),orderId);
                    }
                });
    }
}
