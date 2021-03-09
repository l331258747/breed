package com.play.breed.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.user.TeamBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    Context mContext;
    List<TeamBean> datas;

    public TeamAdapter(Context context, List<TeamBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final TeamBean data = datas.get(position);
        if (data == null) return;

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<TeamBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<TeamBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<TeamBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
