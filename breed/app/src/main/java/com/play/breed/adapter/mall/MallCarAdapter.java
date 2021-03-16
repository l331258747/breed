package com.play.breed.adapter.mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.mall.MallCarBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MallCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<MallCarBean> datas;

    public MallCarAdapter(Context context, List<MallCarBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mall_car, parent, false);
            return new ViewHolderItem(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_mall_car_title, parent,false);
            return new ViewHolderTitle(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ViewHolderTitle) {
            if (holder == null) return;
            final MallCarBean data = datas.get(position);
            if (data == null) return;

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
            }
        } else if (holder instanceof ViewHolderItem) {
            if (holder == null) return;
            final MallCarBean data = datas.get(position);
            if (data == null) return;

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).isTitle()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MallCarBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MallCarBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<MallCarBean> getData() {
        return this.datas;
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);

        }
    }

    public class ViewHolderTitle extends RecyclerView.ViewHolder {

        public ViewHolderTitle(@NonNull View itemView) {
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
