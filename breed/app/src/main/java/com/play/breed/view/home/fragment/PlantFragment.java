package com.play.breed.view.home.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class PlantFragment extends BreedPlantFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setType(1);
    }

}
