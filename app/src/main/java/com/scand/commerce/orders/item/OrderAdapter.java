package com.scand.commerce.orders.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.scand.commerce.R;
import com.scand.commerce.orders.OrdersPresenter;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private final OrdersPresenter ordersPresenter;
    private final OnItemClickListener onItemClickListener;

    public OrderAdapter(OrdersPresenter ordersPresenter) {
        this.ordersPresenter = ordersPresenter;
        this.onItemClickListener = ordersPresenter.getOnItemClickListener();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orders, parent, false),
                onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ordersPresenter.onBindVolumeFileViewAtPosition(position, holder.getOnItemCallBack());
    }

    @Override
    public int getItemCount() {
        return ordersPresenter.getVolumeFilesCount();
    }


    public interface OnItemClickListener {
        void onItemCLick(int position);
    }
}
