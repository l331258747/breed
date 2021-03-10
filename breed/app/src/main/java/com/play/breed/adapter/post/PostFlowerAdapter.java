package com.play.breed.adapter.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.play.breed.R;
import com.play.breed.bean.post.PostFlowerBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostFlowerAdapter extends RecyclerView.Adapter<PostFlowerAdapter.ViewHolder> {

    Context mContext;
    List<PostFlowerBean> datas;

    public PostFlowerAdapter(Context context, List<PostFlowerBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_post_flower, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final PostFlowerBean data = datas.get(position);
        if (data == null) return;


        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<PostFlowerBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<PostFlowerBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<PostFlowerBean> getData() {
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
