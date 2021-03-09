package com.play.breed.view.order;

import android.content.Intent;
import android.os.Bundle;

import com.play.breed.R;
import com.play.breed.adapter.base.EndlessRecyclerOnScrollListener;
import com.play.breed.adapter.base.LoadMoreWrapper;
import com.play.breed.adapter.order.OrderListAdapter;
import com.play.breed.base.BaseFragment;
import com.play.breed.bean.order.OrderListBean;
import com.play.breed.constant.Constant;
import com.play.breed.dialog.TextDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class OrderListFragment extends BaseFragment {

    public static final int ORDER_ALL = 0;
    public static final int ORDER_PAYMENT = 1;
    public static final int ORDER_DELIVER = 2;
    public static final int ORDER_RECEIVING = 3;

    private int orderType;

    SwipeRefreshLayout swipe;

    private RecyclerView recyclerView;
    private OrderListAdapter mAdapter;
    List<OrderListBean> list;

    private boolean isViewCreated;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    public static Fragment newInstance(int orderType) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("orderType", orderType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            orderType = bundle.getInt("orderType");
        }
        isViewCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);//比oncreate先执行
        if (isVisibleToUser && isViewCreated && !isLoad) {
            getRefreshData();
        }
    }



    @Override
    public int getLayoutId() {
        return R.layout.base_swipe_recycler;
    }

    @Override
    public void initView() {
        initSwipe();
        initRecycler();

    }

    @Override
    public void initData() {
        if (getUserVisibleHint()) {
            page = Constant.DEFAULT_PAGE;
            getRefreshData();
        }
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        getList(page);
    }

    private void getList(int page) {
        List<OrderListBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            OrderListBean item = new OrderListBean();
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

    int payPosition;
    //初始化recyclerview
    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new OrderListAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onCancelClick(int position) {
                new TextDialog(context).setContent("是否确认取消订单？").setSubmitListener(v1 -> {
//                    mPresenter.cancelOrder(MySelfInfo.getInstance().getUserId(),datas.get(position).getId());
                }).show();
            }

            @Override
            public void onPayClick(int position) {
                payPosition = position;
                new TextDialog(context).setContent("是否确认付款？").setSubmitListener(v1 -> {
//                mInfoPresenter.mine(MySelfInfo.getInstance().getUserId(),true);
                }).show();

            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context,OrderDetailActivity.class);
//                intent.putExtra("orderId",datas.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onReceiveClick(int position) {
                new TextDialog(context).setContent("是否确认收货？").setSubmitListener(v1 -> {
//                    mPresenter.receiveOrder(MySelfInfo.getInstance().getUserId(),datas.get(position).getId());
                }).show();
            }
        });
    }

}
