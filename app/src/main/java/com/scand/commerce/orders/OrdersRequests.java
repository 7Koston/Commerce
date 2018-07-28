package com.scand.commerce.orders;

import com.scand.commerce.api.RetrofitClient;
import com.scand.commerce.orders.item.OrderModel;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class OrdersRequests {

    Single<ArrayList<OrderModel>> orders() {
        return RetrofitClient.get()
                .getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
