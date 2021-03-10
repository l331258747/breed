package com.play.breed.view.post;

import android.os.Bundle;

import com.play.breed.R;
import com.play.breed.adapter.post.PostFlowerAdapter;
import com.play.breed.base.BaseFragment;
import com.play.breed.bean.post.PostFlowerBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostFlowerFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private PostFlowerAdapter mAdapter;
    List<PostFlowerBean> list;

    public static Fragment newInstance() {
        PostFlowerFragment fragment = new PostFlowerFragment();
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
        List<PostFlowerBean> data = new ArrayList<>();
        for (int i=0;i<40;i++){
            PostFlowerBean item = new PostFlowerBean();
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
        mAdapter = new PostFlowerAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

    }
}
