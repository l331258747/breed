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

/**
 * Created by LGQ
 * Time: 2018/8/15
 * Function:
 */

public class RandomView extends LinearLayout implements View.OnClickListener {

    ImageView iv_img;
    TextView tv_name;

    public RandomView(Context context) {
        this(context, null);
    }

    public RandomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RandomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_random, this, true);

        iv_img = findViewById(R.id.iv_img);
        tv_name = findViewById(R.id.tv_name);

        setOnClickListener(v -> {
            if(onItemClickListener != null){
                onItemClickListener.onClick(this);
            }
        });

    }

    public ImageView getIv_img() {
        return iv_img;
    }

    public TextView getTv_name() {
        return tv_name;
    }

    OnItemClickListener onItemClickListener;

    public void setCallback(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickListener {
        void onClick(RandomView view);
    }
}
