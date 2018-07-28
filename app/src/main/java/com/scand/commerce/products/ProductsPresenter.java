package com.scand.commerce.products;

import android.os.Bundle;

import com.scand.commerce.LifecycleCallbacks;
import com.scand.commerce.products.item.ItemAdapter;
import com.scand.commerce.products.item.ItemCallBack;
import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class ProductsPresenter extends LifecycleCallbacks implements ItemAdapter.OnItemClickListener {

    private static final String BUNDLE_DOWNLOADS = "BUNDLE_PRODUCTS";
    private final ProductsView productsView;
    private final ProductsRequests productsRequests;
    private ArrayList<ItemModel> itemModels;

    ProductsPresenter(ProductsView productsView) {
        this.productsView = productsView;
        productsRequests = new ProductsRequests();
    }

    private void loadProducts() {
        Disposable subscription = productsRequests.items()
                .subscribeWith(new DisposableSingleObserver<ArrayList<ItemModel>>() {
                    @Override
                    public void onSuccess(ArrayList<ItemModel> models) {
                        if (models != null && !models.isEmpty()) {
                            itemModels = models;
                            productsView.onProductsLoaded(itemModels);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        productsView.onErrorMessage("Error");
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void onItemCLick(int position) {
        productsView.onItemClicked(itemModels.get(position), position);
    }


    void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            itemModels = savedInstanceState.getParcelableArrayList(BUNDLE_DOWNLOADS);
            if (itemModels != null)
                productsView.onProductsLoaded(itemModels);
        } else
            loadProducts();
    }

    void refresh() {
        clearList();
        loadProducts();
    }

    void onSaveInstanceState(Bundle outState) {
        if (itemModels != null) {
            outState.putParcelableArrayList(BUNDLE_DOWNLOADS, itemModels);
        }
    }

    int clearList() {
        int size = 0;
        if (itemModels != null) {
            size = itemModels.size();
            itemModels.clear();
        }
        return size;
    }

    public void onBindVolumeFileViewAtPosition(int position, ItemCallBack callBack) {
        ItemModel item = itemModels.get(position);
        callBack.setTitle(item.getTitle());
        callBack.setPrice(item.getPrice());
        callBack.setImageUrl(item.getImage());
    }

    public int getVolumeFilesCount() {
        if (itemModels != null)
            return itemModels.size();
        else return 0;
    }

    public ItemAdapter.OnItemClickListener getOnItemClickListener() {
        return this;
    }
}
