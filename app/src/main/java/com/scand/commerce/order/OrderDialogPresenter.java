package com.scand.commerce.order;

import android.os.Bundle;

import com.scand.commerce.LifecycleCallbacks;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;


class OrderDialogPresenter extends LifecycleCallbacks {

    private final OrderDialogView orderDialogView;
    private final OrderDialogRequests orderDialogRequests;
    private String objectId;

    OrderDialogPresenter(OrderDialogView view, String objectId) {
        this.orderDialogView = view;
        orderDialogRequests = new OrderDialogRequests();
        this.objectId = objectId;
    }

    void sendOrder() {
        Disposable subscription = orderDialogRequests.addOrder(orderDialogView
                .onOrderModelRequested(objectId))
                .subscribeWith(new DisposableSingleObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        orderDialogView.onPostFinished("Thanks for your order");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        orderDialogView.onErrorMessage("Error");
                    }
                });
        addSubscription(subscription);
    }


    void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            objectId = savedInstanceState.getString("objectId");
        }
    }

    void onSaveInstanceState(Bundle outState) {
        if (objectId != null) {
            outState.putString("objectId", objectId);
        }
    }
}
