package com.play.breed.view.mall;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.base.BaseFragment;
import com.play.breed.util.StatusBarUtil;
import com.play.breed.view.mall.fragment.MallCarFragment;
import com.play.breed.view.mall.fragment.MallClassifyFragment;
import com.play.breed.view.mall.fragment.MallGoodsFragment;
import com.play.breed.view.mall.fragment.MallHomeFragment;
import com.play.breed.view.mall.fragment.MallOrderFragment;
import com.play.breed.widget.tab.TabItem;
import com.play.breed.widget.tab.TabLayout;
import com.play.breed.widget.tab.TabView;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MallActivity extends BaseActivity implements TabLayout.OnTabClickListener {
    private TabLayout tabLayout;
    private ArrayList<TabItem> tabItems;

    private Class[] fragmentCls = new Class[5];
    private Fragment[] fragments = new Fragment[5];

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tabLayout = $(R.id.cus_tab_layout);
    }


    @Override
    public void initData() {
        tabItems = new ArrayList<>();
        fragmentCls = new Class[5];
        fragments = new Fragment[5];

        // 初始化页面
        try {
            fragmentCls[0] = MallHomeFragment.class;
            fragmentCls[1] = MallClassifyFragment.class;
            fragmentCls[2] = MallGoodsFragment.class;
            fragmentCls[3] = MallCarFragment.class;
            fragmentCls[4] = MallOrderFragment.class;

            fragments[0] = (BaseFragment) fragmentCls[0].newInstance();
            fragments[1] = (BaseFragment) fragmentCls[1].newInstance();
            fragments[2] = (BaseFragment) fragmentCls[2].newInstance();
            fragments[3] = (BaseFragment) fragmentCls[3].newInstance();
            fragments[4] = (BaseFragment) fragmentCls[4].newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        tabItems.add(new TabItem(R.drawable.tab_mall_home, R.string.str_tab_mall_home, 0, fragmentCls[0]));
        tabItems.add(new TabItem(R.drawable.tab_mall_classify, R.string.str_tab_mall_classify, 0, fragmentCls[1]));
        tabItems.add(new TabItem(R.drawable.tab_mall_goods, R.string.str_tab_mall_goods, 0, fragmentCls[2]));
        tabItems.add(new TabItem(R.drawable.tab_mall_car, R.string.str_tab_mall_car, 0, fragmentCls[3]));
        tabItems.add(new TabItem(R.drawable.tab_mall_order, R.string.str_tab_mall_order, 0, fragmentCls[4]));

        tabLayout.initData(tabItems, this);

        setTabIndex(0);
    }

    public void setTabIndex(int i) {
        onTabItemClick(tabItems.get(i));
    }

    //设置标题
    public void setTitleSingle(boolean showTitle, String title) {
        if (showTitle) {
            showLeftAndTitle(title);
        } else {
            hideTitleLayout();
        }
    }

    /**
     * @param badgeNum
     */
    public void changeTabBadge(int badgeNum) {
        TabView tabView = tabLayout.getTabView();
        if (null != tabView) {
            if (badgeNum > 0) {
                tabView.setDotNum(-1);
            } else {
                tabView.setDotNum(badgeNum);
            }
        }
        tabView.requestFocus();
        tabView.invalidate();
    }

    @Override
    public void onTabItemClick(TabItem tabItem) {

        int index = 0;
        index = tabItems.indexOf(tabItem);

        switch (index) {
            case 0:
                setTitleSingle(true, getResString(R.string.str_tab_mall_home));
                break;
            case 1:
                setTitleSingle(true, getResString(R.string.str_tab_mall_classify));
                break;
            case 2:
                setTitleSingle(true, getResString(R.string.str_tab_mall_goods));
                break;
            case 3:
                setTitleSingle(true, getResString(R.string.str_tab_mall_car));
                break;
            case 4:
                setTitleSingle(true, getResString(R.string.str_tab_mall_order));
                break;
        }

        StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.white));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment.isAdded()) {
                transaction.hide(fragment);
            }
        }

        try {
            tabLayout.setTabSelect(index);

            if (fragments[index].isAdded()) {
                transaction.show(fragments[index]).commitAllowingStateLoss();
            } else {
                transaction.add(R.id.cus_tab_fragment, fragments[index]).commitAllowingStateLoss();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //防止fragment混淆
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
    }
}
