package com.play.breed.view.post;

import com.play.breed.R;
import com.play.breed.adapter.base.BaseFragmentAdapter;
import com.play.breed.base.BaseActivity;
import com.play.breed.widget.myTabLayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class PostDetailActivity extends BaseActivity {

    public String[] titles = {"评论", "赞", "送花"};
    public List<Fragment> mFragments;
    public ViewPager mViewPager;
    public TabLayout mTabLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_post_detail;
    }

    @Override
    public void initView() {
        showLeftAndTitle("帖子详情");

        mViewPager = $(R.id.viewpager);
        mTabLayout = $(R.id.tablayout);

        initViewPage();
    }

    @Override
    public void initData() {

    }

    public  void initViewPage(){
        mFragments = new ArrayList<>();
        mFragments.add(PostCommentFragment.newInstance());
        mFragments.add(PostZanFragment.newInstance());
        mFragments.add(PostFlowerFragment.newInstance());

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
