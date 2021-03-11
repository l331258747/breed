package com.play.breed.adapter.mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.mall.MallHomeStoreBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MallHomeStoreAdapter extends RecyclerView.Adapter<MallHomeStoreAdapter.ViewHolder> {

    Context mContext;
    List<MallHomeStoreBean> datas;

    public MallHomeStoreAdapter(Context context, List<MallHomeStoreBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_mall_home_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final MallHomeStoreBean data = datas.get(position);
        if (data == null) return;

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MallHomeStoreBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MallHomeStoreBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<MallHomeStoreBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
