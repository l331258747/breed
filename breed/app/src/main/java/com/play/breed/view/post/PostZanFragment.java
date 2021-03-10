package com.play.breed.view.post;

import android.os.Bundle;

import com.play.breed.R;
import com.play.breed.adapter.post.PostZanAdapter;
import com.play.breed.base.BaseFragment;
import com.play.breed.bean.post.PostZanBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostZanFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private PostZanAdapter mAdapter;
    List<PostZanBean> list;

    public static Fragment newInstance() {
        PostZanFragment fragment = new PostZanFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.base_recycler;
    }

    @Override
    public void initView() {
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
        List<PostZanBean> data = new ArrayList<>();
        for (int i=0;i<40;i++){
            PostZanBean item = new PostZanBean();
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
        mAdapter = new PostZanAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

    }
}
