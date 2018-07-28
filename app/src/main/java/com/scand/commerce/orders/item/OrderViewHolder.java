package com.scand.commerce.orders.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scand.commerce.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements OrderCallBack,
        CardView.OnClickListener {

    private final TextView tvOrderName;
    private final TextView tvOrderPhone;
    private final CardView cvOrder;

    private final OrderAdapter.OnItemClickListener onItemClickListener;

    OrderViewHolder(@NonNull View view, OrderAdapter.OnItemClickListener callBack) {
        super(view);
        onItemClickListener = callBack;
        tvOrderPhone = itemView.findViewById(R.id.tvOrderPhone);
        tvOrderName = itemView.findViewById(R.id.tvOrderName);
        cvOrder = itemView.findViewById(R.id.cvOrder);
        cvOrder.setOnClickListener(this);
    }

    OrderCallBack getOnItemCallBack() {
        return this;
    }

    @Override
    public void setName(String name) {
        tvOrderPhone.setText(name);
    }

    @Override
    public void setPhone(String phone) {
        tvOrderName.setText(String.valueOf(phone));
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemCLick(getAdapterPosition());
    }
}
