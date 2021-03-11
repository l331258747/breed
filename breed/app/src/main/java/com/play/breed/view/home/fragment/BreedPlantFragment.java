package com.play.breed.view.home.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseFragment;
import com.play.breed.bean.breed.RandomBean;
import com.play.breed.dialog.TaskDialog;
import com.play.breed.view.breed.BreedMarketActivity;
import com.play.breed.view.breed.BreedMyActivity;
import com.play.breed.view.breed.BreedSellActivity;
import com.play.breed.view.web.WebTextActivity;
import com.play.breed.widget.randomlayout.FlyLayout;
import com.play.breed.widget.randomlayout.RandomLayout;

import java.util.ArrayList;
import java.util.List;

abstract class BreedPlantFragment extends BaseFragment implements View.OnClickListener {

    TextView tv_sign, tv_number, tv_task, tv_market, tv_sell, tv_guide, tv_vaccines, tv_feed;
    View view_breed;

    int type;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_breed;
    }

    @Override
    public void initView() {

        view_breed = $(R.id.view_breed);

        tv_sign = $(R.id.tv_sign);
        tv_number = $(R.id.tv_number);
        tv_task = $(R.id.tv_task);
        tv_market = $(R.id.tv_market);
        tv_sell = $(R.id.tv_sell);
        tv_guide = $(R.id.tv_guide);
        tv_vaccines = $(R.id.tv_vaccines);
        tv_feed = $(R.id.tv_feed);

        tv_sign.setOnClickListener(this);
        tv_number.setOnClickListener(this);
        tv_task.setOnClickListener(this);
        tv_market.setOnClickListener(this);
        tv_sell.setOnClickListener(this);
        tv_guide.setOnClickListener(this);
        tv_vaccines.setOnClickListener(this);
        tv_feed.setOnClickListener(this);

        initRandom();
    }

    private void initRandom() {
        List<RandomBean> datas = new ArrayList<>();
        for (int i=0;i<5;i++){
            RandomBean item = new RandomBean();
            item.setTitle("" + i);
            item.setType(0);
            datas.add(item);
        }

        for (int i=0;i<8;i++){
            RandomBean item = new RandomBean();
            item.setTitle("" + i);
            item.setType(1);
            datas.add(item);
        }

        FlyLayout flyLayout = $(R.id.rl);
        flyLayout.setData(datas,type);
        flyLayout.setOnFlyEverythingListener(new FlyLayout.OnFlyEverythingListener() {
            @Override
            public void onItemClick(View view, int position, String text) {
                showShortToast(text);
            }

            @Override
            public void onAnimationEnd(RandomLayout randomLayout, int animationCount) {
                Log.d("test", "randomLayout:" + randomLayout);
            }
        });
    }


    @Override
    public void initData() {
        tv_number.setText(type == 1 ?"当前植物数量：8":"当前动物数量：8");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign:

                break;
            case R.id.tv_number:
                startActivity(new Intent(context, BreedMyActivity.class));
                break;
            case R.id.tv_task:
                new TaskDialog(context).setTitle(type == 1 ? "植物饲养任务" : "动物饲养任务").show();
                break;
            case R.id.tv_market:
                startActivity(new Intent(context, BreedMarketActivity.class));
                break;
            case R.id.tv_sell:
                startActivity(new Intent(context, BreedSellActivity.class));
                break;
            case R.id.tv_guide:
                Intent intent = new Intent(context, WebTextActivity.class);
                intent.putExtra("web_text", "养殖指引");
                startActivity(intent);
                break;
            case R.id.tv_vaccines:

                break;
            case R.id.tv_feed:

                break;

        }
    }
}
