package com.scand.commerce.order;

import com.scand.commerce.orders.item.OrderModel;

interface OrderDialogView {

    void onPostFinished(String response);

    OrderModel onOrderModelRequested(String objectId);

    void onErrorMessage(String error);
}
