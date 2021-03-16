package com.play.breed.view.mall;

import android.graphics.Paint;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;

public class MallGoodsDetailActivity extends BaseActivity {

    TextView tv_marking_price;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mall_goods_detail;
    }

    @Override
    public void initView() {
        showLeftAndTitle("商品详情");

        tv_marking_price = $(R.id.tv_marking_price);
        tv_marking_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

    }

    @Override
    public void initData() {

    }
}
