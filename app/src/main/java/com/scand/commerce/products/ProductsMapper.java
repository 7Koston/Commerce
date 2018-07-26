package com.scand.commerce.products;

import com.scand.commerce.api.RetrofitClient;
import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductsMapper {

    public Single<ArrayList<ItemModel>> items() {
        return RetrofitClient.get()
                .getItems()
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
