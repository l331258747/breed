package com.play.breed.view.home.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseFragment;
import com.play.breed.view.notice.NoticeActivity;
import com.play.breed.view.notice.NoticeSysActivity;
import com.play.breed.view.order.OrderListActivity;
import com.play.breed.view.user.TeamActivity;
import com.play.breed.view.user.bill.BillHomeActivity;
import com.play.breed.view.user.security.SecurityHomeActivity;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    TextView tv_copy;
    ImageView iv_sys_notify;
    View view_notice, view_order_title;

    LinearLayout ll_order_success,ll_order_pay,ll_order_wait,ll_order_all;
    LinearLayout ll_service_team,ll_service_security,ll_service_bill;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {

        tv_copy = $(R.id.tv_copy);
        iv_sys_notify = $(R.id.iv_sys_notify);
        view_notice = $(R.id.view_notice);

        view_order_title = $(R.id.view_order_title);
        ll_order_success = $(R.id.ll_order_success);
        ll_order_pay = $(R.id.ll_order_pay);
        ll_order_wait = $(R.id.ll_order_wait);
        ll_order_all = $(R.id.ll_order_all);

        ll_service_security = $(R.id.ll_service_security);
        ll_service_team = $(R.id.ll_service_team);
        ll_service_bill = $(R.id.ll_service_bill);

        tv_copy.setOnClickListener(this);
        iv_sys_notify.setOnClickListener(this);
        view_notice.setOnClickListener(this);

        view_order_title.setOnClickListener(this);
        ll_order_success.setOnClickListener(this);
        ll_order_pay.setOnClickListener(this);
        ll_order_wait.setOnClickListener(this);
        ll_order_all.setOnClickListener(this);

        ll_service_team.setOnClickListener(this);
        ll_service_security.setOnClickListener(this);
        ll_service_bill.setOnClickListener(this);

    }


    @Override
    public void initData() {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_copy:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", "这里是要复制的文字");
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                showShortToast("复制成功");
                break;
            case R.id.iv_sys_notify:
                startActivity(new Intent(context, NoticeSysActivity.class));
                break;
            case R.id.view_notice:
                startActivity(new Intent(context, NoticeActivity.class));
                break;
            case R.id.view_order_title:
            case R.id.ll_order_all:
            case R.id.ll_order_wait:
            case R.id.ll_order_pay:
            case R.id.ll_order_success:
                startActivity(new Intent(context, OrderListActivity.class));
                break;
            case R.id.ll_service_team:
                startActivity(new Intent(context, TeamActivity.class));
                break;
            case R.id.ll_service_security:
                startActivity(new Intent(context, SecurityHomeActivity.class));
                break;
            case R.id.ll_service_bill:
                startActivity(new Intent(context, BillHomeActivity.class));
                break;

        }
    }
}
