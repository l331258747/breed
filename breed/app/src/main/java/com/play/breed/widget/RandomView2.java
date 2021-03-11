package com.play.breed.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.play.breed.R;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;

/**
 * Created by LGQ
 * Time: 2018/8/15
 * Function:
 */

public class RandomView2 extends LinearLayout implements View.OnClickListener {

    ImageView iv_img;
    TextView tv_time;
    ContentLoadingProgressBar progress;

    public RandomView2(Context context) {
        this(context, null);
    }

    public RandomView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RandomView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_random2, this, true);

        iv_img = findViewById(R.id.iv_img);
        tv_time = findViewById(R.id.tv_time);
        progress = findViewById(R.id.progress);

        setOnClickListener(v -> {
            if(onItemClickListener != null){
                onItemClickListener.onClick(this);
            }
        });

    }

    public ImageView getIv_img() {
        return iv_img;
    }

    public TextView getTv_time() {
        return tv_time;
    }

    OnItemClickListener onItemClickListener;

    public void setCallback(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickListener {
        void onClick(RandomView2 view);
    }
}
