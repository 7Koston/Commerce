package com.scand.commerce.api;

import com.scand.commerce.orders.item.OrderModel;
import com.scand.commerce.product.ProductModel;
import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIModel {

    @GET("item")
    Single<ArrayList<ItemModel>> getItems();

    @GET("item/{objectid}")
    Single<ProductModel> getItem(@Path("objectid") Object objectId);

    @GET("order")
    Single<ArrayList<OrderModel>> getOrders();

    @POST("order")
    Single<Object> addOrder(@Body OrderModel orderModel);
}
