package com.scand.commerce.orders;


import com.scand.commerce.orders.item.OrderModel;

import java.util.ArrayList;

interface OrdersView {

    void onOrdersLoaded(ArrayList<OrderModel> models);

    void onItemClicked(OrderModel model, int position);

    void onErrorMessage(String error);
}
