package com.play.breed.view.home.fragment;

import android.os.Bundle;

import com.play.breed.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class PlantFragment extends BreedPlantFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setType(1);
    }

    @Override
    public void initView() {
        super.initView();

        view_breed.setBackground(ContextCompat.getDrawable(context, R.mipmap.bg_shanlin));
    }

}
