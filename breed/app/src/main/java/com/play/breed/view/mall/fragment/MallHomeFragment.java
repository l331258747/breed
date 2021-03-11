package com.play.breed.view.mall.fragment;

import com.play.breed.R;
import com.play.breed.adapter.base.EndLessScrollOnScrollListener;
import com.play.breed.adapter.base.LoadMoreWrapper;
import com.play.breed.adapter.mall.MallHomeClassifyAdapter;
import com.play.breed.adapter.mall.MallHomeGoodsAdapter;
import com.play.breed.adapter.mall.MallHomeStoreAdapter;
import com.play.breed.base.BaseFragment;
import com.play.breed.bean.mall.BannerBean;
import com.play.breed.bean.mall.MallHomeClassifyBean;
import com.play.breed.bean.mall.MallHomeGoodsBean;
import com.play.breed.bean.mall.MallHomeStoreBean;
import com.play.breed.constant.Constant;
import com.play.breed.util.glide.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MallHomeFragment extends BaseFragment {

    Banner banner;
    SwipeRefreshLayout swipe;
    NestedScrollView scrollView;
    RecyclerView recycler_view_card,recycler_view_h,recycler_view;

    private MallHomeClassifyAdapter mAdapterClassify;
    private MallHomeStoreAdapter mAdapterStore;
    private MallHomeGoodsAdapter mAdapterGoods;

    List<MallHomeClassifyBean> listClassify;
    List<MallHomeStoreBean> listStore;
    List<MallHomeGoodsBean> listGoods;

    int page = Constant.DEFAULT_PAGE;
    LoadMoreWrapper loadMoreWrapper;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall_home;
    }

    @Override
    public void initView() {
        banner = $(R.id.banner);
        scrollView = $(R.id.scrollView);

        initSwipe();
        initRecyclerClassify();
        initRecyclerStore();
        initRecyclerGoods();

    }

    @Override
    public void initData() {
        page = Constant.DEFAULT_PAGE;
        getRefreshData();
        getListClassifyList();
        getListStoreList();
        setBanner();
    }

    private void getListClassifyList() {
        List<MallHomeClassifyBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            MallHomeClassifyBean item = new MallHomeClassifyBean();
            data.add(item);
        }
        if(data == null) data = new ArrayList<>();

        mAdapterClassify.setData(data);
        this.listClassify = mAdapterClassify.getData();
    }

    private void getListStoreList() {
        List<MallHomeStoreBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            MallHomeStoreBean item = new MallHomeStoreBean();
            data.add(item);
        }
        if(data == null) data = new ArrayList<>();

        mAdapterStore.setData(data);
        this.listStore = mAdapterStore.getData();
    }

    public void getRefreshData() {
        swipe.setRefreshing(true);
        page = Constant.DEFAULT_PAGE;
        isLoad = true;
        isLoadType = 1;
        getListGoods(page);
    }

    private void getListGoods(int page) {
        List<MallHomeGoodsBean> data = new ArrayList<>();
        for (int i=0;i<10;i++){
            MallHomeGoodsBean item = new MallHomeGoodsBean();
            data.add(item);
        }

        if(data == null) data = new ArrayList<>();

        swipe.setRefreshing(false);

        if (isLoadType == 1) {
            this.listGoods = data;
            mAdapterGoods.setData(data);
        } else {
            mAdapterGoods.addData(data);
        }
        this.listGoods = mAdapterGoods.getData();

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
        getListGoods(page);
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
    private void initRecyclerGoods() {
        recycler_view = $(R.id.recycler_view);
        recycler_view.setLayoutManager(new GridLayoutManager(activity,  2));
        mAdapterGoods = new MallHomeGoodsAdapter(activity, new ArrayList<>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapterGoods);
        recycler_view.setAdapter(loadMoreWrapper);
        recycler_view.setNestedScrollingEnabled(false);

        mAdapterGoods.setOnItemClickListener(position -> {

        });

        scrollView.setOnScrollChangeListener(new EndLessScrollOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }

            @Override
            public void onScrollChange(int scrollY) {

            }
        });
    }

    //初始化recyclerview
    private void initRecyclerClassify() {
        recycler_view_card= $(R.id.recycler_view_card);
        recycler_view_card.setLayoutManager(new GridLayoutManager(activity,  5));
        mAdapterClassify = new MallHomeClassifyAdapter(activity, new ArrayList<>());
        recycler_view_card.setAdapter(mAdapterClassify);
        recycler_view_card.setNestedScrollingEnabled(false);

        mAdapterClassify.setOnItemClickListener(position -> {

        });
    }

    //初始化recyclerview
    private void initRecyclerStore() {
        recycler_view_h= $(R.id.recycler_view_h);
        recycler_view_h.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        mAdapterStore = new MallHomeStoreAdapter(activity, new ArrayList<>());
        recycler_view_h.setAdapter(mAdapterStore);
        recycler_view_h.setNestedScrollingEnabled(false);

        mAdapterStore.setOnItemClickListener(position -> {

        });
    }

    public void setBanner() {
        List<BannerBean> datas = new ArrayList<>();
        for (int i=0;i<4;i++){
            BannerBean item = new BannerBean();
            item.setImgUrl("https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/banner3.png");
            datas.add(item);
        }

        banner.setAdapter(new BannerImageAdapter<BannerBean>(datas) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                GlideUtil.loadRoundImage(context, data.getImgUrl(), holder.imageView, 10);
            }
        }).addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(context));
    }
}
