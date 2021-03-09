package com.play.breed.adapter.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.bean.notice.NoticeBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    Context mContext;
    List<NoticeBean> datas;

    public NoticeAdapter(Context context, List<NoticeBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final NoticeBean data = datas.get(position);
        if (data == null) return;

//        holder.tv_title.setText(data.getTitle());
//        holder.tv_content.setText(data.getContent());
//        holder.tv_time.setText(data.getTime());



        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<NoticeBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<NoticeBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<NoticeBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_content,tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_time);

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
