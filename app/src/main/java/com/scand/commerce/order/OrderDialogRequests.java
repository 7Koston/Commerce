package com.scand.commerce.order;

import com.scand.commerce.api.RetrofitClient;
import com.scand.commerce.orders.item.OrderModel;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class OrderDialogRequests {

    Single<Object> addOrder(OrderModel model) {
        return RetrofitClient.get()
                .addOrder(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
