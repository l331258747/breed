package com.play.breed.adapter.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.play.breed.R;
import com.play.breed.bean.address.AddressBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context mContext;
    List<AddressBean> datas;

    public AddressAdapter(Context context, List<AddressBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder == null) return;
        final AddressBean data = datas.get(position);
        if (data == null) return;


        if (mOnItemClickListener != null) {
            holder.btn_edit.setOnClickListener(v -> mOnItemClickListener.onClick(position));

            holder.itemView.setOnLongClickListener(v -> {
                mOnItemClickListener.onLongClick(position);
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<AddressBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
