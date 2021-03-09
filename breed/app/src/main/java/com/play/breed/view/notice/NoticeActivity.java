package com.play.breed.view.notice;

import android.content.Intent;

import com.play.breed.R;
import com.play.breed.adapter.base.EndlessRecyclerOnScrollListener;
import com.play.breed.adapter.base.LoadMoreWrapper;
import com.play.breed.adapter.notice.NoticeAdapter;
import com.play.breed.base.BaseActivity;
import com.play.breed.bean.notice.NoticeBean;
import com.play.breed.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class NoticeActivity extends BaseActivity {

    SwipeRefreshLayout swipe;

    private RecyclerView recyclerView;

    private NoticeAdapter mAdapter;

    List<NoticeBean> list;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    @Override
    public int getLayoutId() {
        return R.layout.base_swipe_recycler;
    }

    @Override
    public void initView() {
        showLeftAndTitle("公告");
        initSwipe();
        initRecycler();
    }

    @Override
    public void initData() {
        page = Constant.DEFAULT_PAGE;
        getRefreshData();
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        getList(page);
    }

    private void getList(int page) {
        List<NoticeBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            NoticeBean item = new NoticeBean();
            data.add(item);
        }

        if(data == null) data = new ArrayList<>();

        swipe.setRefreshing(false);

        if (isLoadType == 1) {
            this.list = data;
            mAdapter.setData(data);
        } else {
            mAdapter.addData(data);
        }
        this.list = mAdapter.getData();

        isLoad = false;

        if (data.size() >= Constant.DEFAULT_SIZE) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }

    }

    public void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        getList(page);
    }

    private void initSwipe() {
        swipe = $(R.id.swipe);
        swipe.setColorSchemeResources(R.color.color_10AF4F);
        swipe.setOnRefreshListener(() -> {
            if (isLoad) return;
            getRefreshData();
        });
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new NoticeAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        mAdapter.setOnItemClickListener(position -> {
            startActivity(new Intent(context,NoticeDetailActivity.class));
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });
    }
}