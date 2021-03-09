package com.play.breed.adapter.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.notice.NoticeSysBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeSysAdapter extends RecyclerView.Adapter<NoticeSysAdapter.ViewHolder> {

    Context mContext;
    List<NoticeSysBean> datas;

    public NoticeSysAdapter(Context context, List<NoticeSysBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_notice_sys, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final NoticeSysBean data = datas.get(position);
        if (data == null) return;

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<NoticeSysBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<NoticeSysBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<NoticeSysBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
