package com.play.breed.widget.randomlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.play.breed.bean.breed.RandomBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by wkp111 on 2017/4/3.
 */

public class FlyLayout extends FrameLayout implements RandomLayout.OnItemClickListener, RandomLayout.OnAnimationEndListener {

    private Context mContext;

    public FlyLayout(Context context) {
        this(context, null);
    }

    public FlyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setData(@NonNull List<RandomBean> param,int type) {
        removeAllViews();
        RandomLayout randomLayout = new RandomLayout(mContext);
        randomLayout.setData((ArrayList<RandomBean>) param,type);
        randomLayout.setOnItemClickListener(this);
        randomLayout.setOnAnimationEndListener(this);
        addView(randomLayout);
    }

    public interface OnFlyEverythingListener{
        void onItemClick(View view, int position, String text);
        void onAnimationEnd(RandomLayout randomLayout, int animationCount);
    }

    private OnFlyEverythingListener mListener;

    public void setOnFlyEverythingListener(OnFlyEverythingListener listener) {
        mListener = listener;
    }

//    public void startAnimation() {
//        if (getChildCount() > 0) {
//            ((RandomLayout) getChildAt(getChildCount() - 1)).startAnimation();
//        }
//    }

    @Override
    public void onItemClick(View view, int position, String text) {
        if (mListener != null) {
            mListener.onItemClick(view,position,text);
        }
    }

    @Override
    public void onAnimationEnd(RandomLayout randomLayout, int animationCount) {
        removeView(randomLayout);
        addView(randomLayout,0);
        if (mListener != null) {
            mListener.onAnimationEnd(randomLayout,animationCount);
        }
    }
}
