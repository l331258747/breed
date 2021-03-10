package com.play.breed.adapter.breed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.breed.BreedMyBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BreedMyAdapter extends RecyclerView.Adapter<BreedMyAdapter.ViewHolder> {

    Context mContext;
    List<BreedMyBean> datas;

    public BreedMyAdapter(Context context, List<BreedMyBean> datas) {
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
        final BreedMyBean data = datas.get(position);
        if (data == null) return;

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<BreedMyBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<BreedMyBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<BreedMyBean> getData() {
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
