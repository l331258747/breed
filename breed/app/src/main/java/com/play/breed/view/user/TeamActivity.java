package com.play.breed.view.user;

import com.play.breed.R;
import com.play.breed.adapter.user.TeamAdapter;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.user.TeamBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TeamAdapter mAdapter;
    List<TeamBean> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    public void initView() {
        showLeftAndTitle("我的团队");
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
        List<TeamBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            TeamBean item = new TeamBean();
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
        mAdapter = new TeamAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

    }
}
