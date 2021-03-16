package com.play.breed.adapter.mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.bean.mall.MallOrderListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MallOrderListAdapter extends RecyclerView.Adapter<MallOrderListAdapter.ViewHolder> {

    Context mContext;
    List<MallOrderListBean> datas;

    public MallOrderListAdapter(Context context, List<MallOrderListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_mall_order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final MallOrderListBean data = datas.get(position);
        if (data == null) return;


        if (mOnItemClickListener != null) {
            holder.btn_cancel.setOnClickListener(v -> mOnItemClickListener.onCancelClick(position));
            holder.btn_pay.setOnClickListener(v -> mOnItemClickListener.onPayClick(position));
            holder.btn_receive.setOnClickListener(v -> mOnItemClickListener.onReceiveClick(position));
            holder.itemView.setOnClickListener( v -> mOnItemClickListener.onItemClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MallOrderListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MallOrderListBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<MallOrderListBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView btn_cancel,btn_pay,btn_receive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_pay = itemView.findViewById(R.id.btn_pay);
            btn_receive = itemView.findViewById(R.id.btn_receive);

        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onCancelClick(int position);
        void onPayClick(int position);
        void onItemClick(int position);
        void onReceiveClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
