package com.play.breed.view.address;

import android.view.View;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.adapter.address.RegionAdapter;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.address.ListReginBean;
import com.play.breed.util.rxbus.RxBus2;
import com.play.breed.util.rxbus.busEvent.RegionSelEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListRegionActivity extends BaseActivity {

    RecyclerView recyclerView;
    RegionAdapter mAdapter;
    List<ListReginBean> datas;

    TextView tv_name;
    String pCode = "000000";

    String province;
    String city;
    String region;

    String address;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_region;
    }

    @Override
    public void initView() {
        showLeftAndTitle("城市选择");

        tv_name = $(R.id.tv_name);
        tv_name.setVisibility(View.GONE);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RegionAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            ListReginBean item = datas.get(position);
            pCode = item.getCode();

            if(item.getLevel() == 1){
                province = item.getName();
                address = province;
            }else if(item.getLevel() == 2){
                city = item.getName();
                address = address + " > "+city;
            }else if(item.getLevel() == 3){
                region = item.getName();
                address = address + " > "+region;
            }
            tv_name.setVisibility(View.VISIBLE);
            tv_name.setText(address);

            if(item.getLevel() >= 3){
                finish();
                RxBus2.getInstance().post(new RegionSelEvent(province,city,region,pCode));
            }else{
                listRegion(pCode);
            }
        });
    }

    @Override
    public void initData() {
        listRegion(pCode);
    }

    private void listRegion(String pCode) {
        List<ListReginBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            ListReginBean item = new ListReginBean();
            data.add(item);
        }

        if(data == null) data = new ArrayList<>();
        this.datas = data;
        mAdapter.setData(datas);
    }

}
