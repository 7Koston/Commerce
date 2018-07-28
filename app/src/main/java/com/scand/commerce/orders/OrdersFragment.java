package com.scand.commerce.orders;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scand.commerce.R;
import com.scand.commerce.orders.item.OrderAdapter;
import com.scand.commerce.orders.item.OrderModel;
import com.scand.commerce.product.ProductActivity;
import com.scand.commerce.widgets.TintedProgressBar;

import java.util.ArrayList;

public class OrdersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        OrdersView {

    private OrdersPresenter mPresenter;

    private OrderAdapter mAdapter;

    private View v;
    private TintedProgressBar pbOrders;
    private SwipeRefreshLayout srlOrders;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        v = inflater.inflate(R.layout.fragment_orders, container, false);

        Context mContext = v.getContext();

        RecyclerView rvOrders = v.findViewById(R.id.rvOrders);
        pbOrders = v.findViewById(R.id.pbOrders);
        srlOrders = v.findViewById(R.id.srlOrders);

        mPresenter = new OrdersPresenter(this);

        mAdapter = new OrderAdapter(mPresenter);

        rvOrders.setLayoutManager(
                new LinearLayoutManager(mContext));
        rvOrders.setAdapter(mAdapter);
        srlOrders.setOnRefreshListener(this);

        mPresenter.onCreate(savedInstanceState);
        return v;
    }

    @Override
    public void onRefresh() {
        srlOrders.setRefreshing(true);
        mPresenter.refresh();
        mAdapter.notifyItemRangeRemoved(0, mPresenter.clearList());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    public void onOrdersLoaded(ArrayList<OrderModel> models) {
        pbOrders.setVisibility(View.GONE);
        srlOrders.setRefreshing(false);
        mAdapter.notifyItemRangeInserted(0, models.size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(OrderModel model, int position) {
        if (!ProductActivity.isActive) {

            Intent intent = new Intent(getContext(), ProductActivity.class);
            intent.putExtra("objectId", model.getItemId());
            intent.putExtra("fromOrders", true);

            startActivity(intent);
        }
    }

    @Override
    public void onErrorMessage(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();
        pbOrders.setVisibility(View.GONE);
        srlOrders.setRefreshing(false);
    }
}
