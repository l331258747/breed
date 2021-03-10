package com.play.breed.view.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;
import com.play.breed.base.BaseFragment;
import com.play.breed.util.StatusBarUtil;
import com.play.breed.view.home.fragment.BreedFragment;
import com.play.breed.view.home.fragment.MallFragment;
import com.play.breed.view.home.fragment.MyFragment;
import com.play.breed.view.home.fragment.PlantFragment;
import com.play.breed.view.home.fragment.PostFragment;
import com.play.breed.widget.tab.TabItem;
import com.play.breed.widget.tab.TabLayout;
import com.play.breed.widget.tab.TabView;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends BaseActivity implements TabLayout.OnTabClickListener {

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
            fragmentCls[0] = BreedFragment.class;
            fragmentCls[1] = PlantFragment.class;
            fragmentCls[2] = MallFragment.class;
            fragmentCls[3] = PostFragment.class;
            fragmentCls[4] = MyFragment.class;

            fragments[0] = (BaseFragment) fragmentCls[0].newInstance();
            fragments[1] = (BaseFragment) fragmentCls[1].newInstance();
            fragments[2] = (BaseFragment) fragmentCls[2].newInstance();
            fragments[3] = (BaseFragment) fragmentCls[3].newInstance();
            fragments[4] = (BaseFragment) fragmentCls[4].newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        tabItems.add(new TabItem(R.drawable.tab_breed, R.string.str_tab_breed, 0, fragmentCls[0]));
        tabItems.add(new TabItem(R.drawable.tab_plant, R.string.str_tab_plant, 0, fragmentCls[1]));
        tabItems.add(new TabItem(R.drawable.tab_mall, R.string.str_tab_mall, 0, fragmentCls[2]));
        tabItems.add(new TabItem(R.drawable.tab_post, R.string.str_tab_post, 0, fragmentCls[3]));
        tabItems.add(new TabItem(R.drawable.tab_my, R.string.str_tab_my, 0, fragmentCls[4]));

        tabLayout.initData(tabItems, this);

        setTabIndex(0);
    }

    public void setTabIndex(int i) {
        onTabItemClick(tabItems.get(i));
    }

    //设置标题
    public void setTitleSingle(boolean showTitle, String title) {
        if (showTitle) {
            showTitleLayout();
            showTitleTv();
            getTitleTv().setText(title);
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
                setTitleSingle(false, getResString(R.string.str_tab_breed));
                break;
            case 1:
                setTitleSingle(false, getResString(R.string.str_tab_plant));
                break;
            case 2:
                setTitleSingle(true, getResString(R.string.str_tab_mall));
                break;
            case 3:
                setTitleSingle(false, getResString(R.string.str_tab_post));
                break;
            case 4:
                setTitleSingle(false, getResString(R.string.str_tab_my));
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
