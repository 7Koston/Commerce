package com.scand.commerce.product;

public interface ProductView {

    void onProductLoaded(ProductModel productModel);

    void onErrorMessage(String error);
}
