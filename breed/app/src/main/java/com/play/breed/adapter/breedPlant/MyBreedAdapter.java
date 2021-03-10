package com.play.breed.adapter.breedPlant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.breedPlant.MyBreedBean;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyBreedAdapter extends RecyclerView.Adapter<MyBreedAdapter.ViewHolder> {

    Context mContext;
    List<MyBreedBean> datas;

    public MyBreedAdapter(Context context, List<MyBreedBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_breed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final MyBreedBean data = datas.get(position);
        if (data == null) return;

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MyBreedBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MyBreedBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<MyBreedBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
