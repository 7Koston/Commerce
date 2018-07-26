package com.scand.commerce.product;


import com.scand.commerce.api.RetrofitClient;
import com.scand.commerce.products.item.ItemModel;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductMapper {

    public Single<ItemModel> item(Object itemId) {
        return RetrofitClient.get()
                .getItem(itemId)
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
