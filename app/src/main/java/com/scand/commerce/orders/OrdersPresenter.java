package com.scand.commerce.orders;

import android.os.Bundle;

import com.scand.commerce.LifecycleCallbacks;
import com.scand.commerce.orders.item.OrderAdapter;
import com.scand.commerce.orders.item.OrderCallBack;
import com.scand.commerce.orders.item.OrderModel;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class OrdersPresenter extends LifecycleCallbacks implements OrderAdapter.OnItemClickListener {

    private static final String BUNDLE_DOWNLOADS = "BUNDLE_PRODUCTS";
    private final OrdersView ordersView;
    private final OrdersRequests ordersRequests;
    private ArrayList<OrderModel> orderModels;

    OrdersPresenter(OrdersView ordersView) {
        this.ordersView = ordersView;
        ordersRequests = new OrdersRequests();
    }

    private void loadOrders() {
        Disposable subscription = ordersRequests.orders()
                .subscribeWith(new DisposableSingleObserver<ArrayList<OrderModel>>() {
                    @Override
                    public void onSuccess(ArrayList<OrderModel> models) {
                        if (models != null && !models.isEmpty()) {
                            orderModels = models;
                            ordersView.onOrdersLoaded(orderModels);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        ordersView.onErrorMessage("Error");
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void onItemCLick(int position) {
        ordersView.onItemClicked(orderModels.get(position), position);
    }


    void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            orderModels = savedInstanceState.getParcelableArrayList(BUNDLE_DOWNLOADS);
            if (orderModels != null)
                ordersView.onOrdersLoaded(orderModels);
        } else
            loadOrders();
    }

    void refresh() {
        clearList();
        loadOrders();
    }

    void onSaveInstanceState(Bundle outState) {
        if (orderModels != null) {
            outState.putParcelableArrayList(BUNDLE_DOWNLOADS, orderModels);
        }
    }

    int clearList() {
        int size = 0;
        if (orderModels != null) {
            size = orderModels.size();
            orderModels.clear();
        }
        return size;
    }

    public void onBindVolumeFileViewAtPosition(int position, OrderCallBack callBack) {
        OrderModel item = orderModels.get(position);
        callBack.setName(item.getName());
        callBack.setPhone(item.getPhone());
    }

    public int getVolumeFilesCount() {
        if (orderModels != null)
            return orderModels.size();
        else return 0;
    }

    public OrderAdapter.OnItemClickListener getOnItemClickListener() {
        return this;
    }
}
