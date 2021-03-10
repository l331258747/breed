package com.play.breed.view.address;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.adapter.address.AddressAdapter;
import com.play.breed.dialog.DialogUtil;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.MySelfInfo;
import com.play.breed.bean.address.AddressBean;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.AddressEditEvent;
import com.play.breed.util.textdrawable.TextdrawableUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

public class AddressSetActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_head, tv_name, tv_address, btn_edit, btn_submit, tv_phone;
    RecyclerView recyclerView;
    ConstraintLayout default_address;

    AddressAdapter mAdapter;
    List<AddressBean> otherDatas;
    AddressBean defaultData;

    Disposable disposable;

    TextdrawableUtils mTextdrawableUtils;


    @Override
    public int getLayoutId() {
        return R.layout.acitivty_address_set;

    }

    @Override
    public void initView() {
        showLeftAndTitle("收货地址");

        tv_head = $(R.id.tv_head);
        tv_name = $(R.id.tv_name);
        tv_phone = $(R.id.tv_phone);
        tv_address = $(R.id.tv_address);
        btn_edit = $(R.id.btn_edit);
        btn_submit = $(R.id.btn_submit);
        default_address = $(R.id.default_address);

        btn_submit.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        initRecycler();
    }

    @Override
    public void initData() {
        mTextdrawableUtils = new TextdrawableUtils(context, "默认");

        disposable = RxBus2.getInstance().toObservable(AddressEditEvent.class, addressEditEvent ->
                addressList(MySelfInfo.getInstance().getUserId())
        );
    }

    private void addressList(String userId) {
        List<AddressBean> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AddressBean item = new AddressBean();
            item.setId("id");
            item.setAddress("address");
            item.setCity("city");
            item.setMobile("mobile");
            item.setName("name");
            item.setProvince("province");
            item.setUid("uid");
            item.setType(0);
            item.setRegion("region");
            datas.add(item);
            if (i == 0) {
                item.setType(1);
            }
        }

        if (datas == null) datas = new ArrayList<>();
        setDefaultView(datas);
    }

    public void setDefaultView(List<AddressBean> datas) {

        defaultData = getDefaultItem(datas);
        otherDatas = getAddressList(datas);

        if (defaultData != null) {//默认图标
            default_address.setVisibility(View.VISIBLE);
        } else {
            default_address.setVisibility(View.GONE);
        }

        mAdapter.setData(otherDatas);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                intent = new Intent(context, AddressEditActivity.class);
                intent.putExtra("AddressBean", defaultData);
                startActivity(intent);
                break;
            case R.id.btn_submit:
                startActivity(new Intent(context, AddressEditActivity.class));
                break;
        }
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new AddressAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                intent = new Intent(context, AddressEditActivity.class);
                intent.putExtra("AddressBean", otherDatas.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(final int position) {
                DialogUtil.getInstance().getDefaultDialog(context, "确认删除", alterDialog ->
                        addressDelete(MySelfInfo.getInstance().getUserId(), otherDatas.get(position).getId())).show();
            }
        });
    }

    private void addressDelete(String userId, String id) {
        showLongToast("删除成功");
        addressList(MySelfInfo.getInstance().getUserId());
    }

    public AddressBean getDefaultItem(List<AddressBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getType() == 1) {
                return datas.get(i);
            }
        }
        return null;
    }

    public List<AddressBean> getAddressList(List<AddressBean> datas) {
        List<AddressBean> items = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getType() != 1) {
                items.add(datas.get(i));
            }
        }
        return items;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
