package com.scand.commerce.api;

import com.scand.commerce.order.OrderModel;
import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IRetrofitModule {

    @GET("item")
    Observable<ArrayList<ItemModel>> getItems();

    @GET("/item/{objectid}")
    Observable<ItemModel> getItem(@Path("objectid") Object objectId);

    @POST("order")
    String addOrder(@Body OrderModel orderModel);
}
