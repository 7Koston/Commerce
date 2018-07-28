package com.scand.commerce.product;

import android.os.Bundle;

import com.scand.commerce.LifecycleCallbacks;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

class ProductPresenter extends LifecycleCallbacks {
    private final static String BUNDLE_SECTIONS = "BUNDLE_SECTIONS";
    private final ProductView productView;
    private final ProductMapper productMapper;
    private ProductModel productModel;
    private String id;

    ProductPresenter(ProductView productView, String id) {
        this.productView = productView;
        productMapper = new ProductMapper();
        this.id = id;
    }

    private void loadProduct() {
        Disposable updatesSubscription = productMapper.item(id)
                .subscribeWith(new DisposableSingleObserver<ProductModel>() {

                    @Override
                    public void onSuccess(ProductModel model) {
                        productModel = model;
                        productView.onProductLoaded(productModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        productView.onErrorMessage("Error");
                    }

                });
        addSubscription(updatesSubscription);
    }

    void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            productModel = savedInstanceState.getParcelable(BUNDLE_SECTIONS);
            if (productModel != null)
                productView.onProductLoaded(productModel);
        } else {
            loadProduct();
        }
    }

    void onSaveInstanceState(Bundle outState) {
        if (productModel != null) {
            outState.putParcelable(BUNDLE_SECTIONS, productModel);
        }
    }

    String getObjectId() {
        return id;
    }
}
