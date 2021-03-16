package com.play.breed.view.mall;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.base.BaseActivity;

public class MallGoodsDetailActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_marking_price;
    View view_comment_title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mall_goods_detail;
    }

    @Override
    public void initView() {
        showLeftAndTitle("商品详情");

        tv_marking_price = $(R.id.tv_marking_price);
        tv_marking_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线


        view_comment_title = $(R.id.view_comment_title);
        view_comment_title.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_comment_title:
                startActivity(new Intent(context,MallGoodsCommentActivity.class));
                break;
        }
    }
}
