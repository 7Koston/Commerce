package com.scand.commerce.products;

import com.scand.commerce.api.RetrofitClient;
import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ProductsRequests {

    Single<ArrayList<ItemModel>> items() {
        return RetrofitClient.get()
                .getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
