package com.scand.commerce.product;


import com.scand.commerce.api.RetrofitClient;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductMapper {

    public Single<ProductModel> item(Object itemId) {
        return RetrofitClient.get()
                .getItem(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}