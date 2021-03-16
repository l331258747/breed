package com.play.breed.adapter.mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.bean.mall.MallClassifyLeftBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MallClassifyLeftAdapter extends RecyclerView.Adapter<MallClassifyLeftAdapter.ViewHolder> {

    Context mContext;
    List<MallClassifyLeftBean> datas;

    public MallClassifyLeftAdapter(Context context, List<MallClassifyLeftBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_mall_classify_left, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final MallClassifyLeftBean data = datas.get(position);
        if (data == null) return;

        holder.itemView.setBackgroundResource(R.color.color_ededed);
        holder.line.setVisibility(View.GONE);

        if(pos == position){
            holder.itemView.setBackgroundResource(R.color.white);
            holder.line.setVisibility(View.VISIBLE);
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MallClassifyLeftBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MallClassifyLeftBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    int pos;
    public void setPos(int pos){
        this.pos = pos;
        notifyDataSetChanged();
    }

    public List<MallClassifyLeftBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        View line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            line = itemView.findViewById(R.id.line);

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
