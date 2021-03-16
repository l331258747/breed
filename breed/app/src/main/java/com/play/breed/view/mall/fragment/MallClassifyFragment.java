package com.play.breed.view.mall.fragment;

import com.play.breed.R;
import com.play.breed.adapter.mall.MallClassifyLeftAdapter;
import com.play.breed.adapter.mall.MallClassifyRightAdapter;
import com.play.breed.base.BaseFragment;
import com.play.breed.bean.mall.MallClassifyLeftBean;
import com.play.breed.bean.mall.MallClassifyRightBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MallClassifyFragment extends BaseFragment {

    RecyclerView recyclerViewLeft;
    RecyclerView recyclerViewRight;

    private MallClassifyLeftAdapter mAdapterLeft;
    private MallClassifyRightAdapter mAdapterRight;
    List<MallClassifyLeftBean> listLeft;
    List<MallClassifyRightBean> listRight;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall_classify;
    }

    @Override
    public void initView() {
        initRecyclerLeft();
        initRecyclerRight();
    }

    @Override
    public void initData() {
        getListLeft();
        getListRight();
    }

    private void getListLeft() {
        List<MallClassifyLeftBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            MallClassifyLeftBean item = new MallClassifyLeftBean();
            data.add(item);
        }

        if(data == null) data = new ArrayList<>();

        mAdapterLeft.setData(data);

        this.listLeft = mAdapterLeft.getData();
    }

    private void getListRight() {
        List<MallClassifyRightBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            MallClassifyRightBean item = new MallClassifyRightBean();
            data.add(item);
        }

        if(data == null) data = new ArrayList<>();

        mAdapterRight.setData(data);

        this.listRight = mAdapterRight.getData();
    }

    //初始化recyclerview
    private void initRecyclerLeft() {
        recyclerViewLeft = $(R.id.recycler_view_left);
        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapterLeft = new MallClassifyLeftAdapter(activity, new ArrayList<>());
        recyclerViewLeft.setAdapter(mAdapterLeft);

        mAdapterLeft.setOnItemClickListener(position -> {
            mAdapterLeft.setPos(position);
        });

    }

    //初始化recyclerview
    private void initRecyclerRight() {
        recyclerViewRight = $(R.id.recycler_view_right);
        recyclerViewRight.setLayoutManager(new GridLayoutManager(activity,  3));
        mAdapterRight = new MallClassifyRightAdapter(activity, new ArrayList<>());
        recyclerViewRight.setAdapter(mAdapterRight);
    }
}
