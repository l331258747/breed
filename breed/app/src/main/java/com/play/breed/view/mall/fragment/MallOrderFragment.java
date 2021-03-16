package com.play.breed.view.mall.fragment;

import com.play.breed.R;
import com.play.breed.adapter.base.BaseFragmentAdapter;
import com.play.breed.base.BaseFragment;
import com.play.breed.view.order.OrderListFragment;
import com.play.breed.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MallOrderFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private String[] titles = {"全部" ,"待付款","待发货","待收货"};

    MallOrderListFragment fOrderAll;
    MallOrderListFragment fOrderPayment;
    MallOrderListFragment fOrderDeliver;
    MallOrderListFragment fOrderReceiving;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall_order;
    }

    @Override
    public void initView() {
        mTabLayout = $(R.id.tabs);
        mViewPager = $(R.id.viewpager);
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        fOrderAll = (MallOrderListFragment) MallOrderListFragment.newInstance(OrderListFragment.ORDER_ALL);
        fOrderPayment = (MallOrderListFragment) MallOrderListFragment.newInstance(OrderListFragment.ORDER_PAYMENT);
        fOrderDeliver = (MallOrderListFragment) MallOrderListFragment.newInstance(OrderListFragment.ORDER_DELIVER);
        fOrderReceiving = (MallOrderListFragment) MallOrderListFragment.newInstance(OrderListFragment.ORDER_RECEIVING);
        mFragments.add(fOrderAll);
        mFragments.add(fOrderPayment);
        mFragments.add(fOrderDeliver);
        mFragments.add(fOrderReceiving);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
