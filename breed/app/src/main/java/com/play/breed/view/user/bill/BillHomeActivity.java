package com.play.breed.view.user.bill;

import android.content.Intent;

import com.play.breed.R;
import com.play.breed.adapter.user.BillAdapter;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.user.BillBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BillHomeActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private BillAdapter mAdapter;
    List<BillBean> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bill_home;
    }

    @Override
    public void initView() {
        showLeftAndTitle("积分账单");
        initRecycler();

    }

    @Override
    public void initData() {
        getRefreshData();
    }

    public void getRefreshData() {
        getList();
    }

    private void getList() {
        List<BillBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            BillBean item = new BillBean();
            data.add(item);
        }

        if(data == null) data = new ArrayList<>();
        mAdapter.setData(data);

        this.list = mAdapter.getData();

    }


    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new BillAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            startActivity(new Intent(context, BillDetailActivity.class));
        });

    }
}
