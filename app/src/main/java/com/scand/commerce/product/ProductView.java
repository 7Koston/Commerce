package com.scand.commerce.product;

interface ProductView {

    void onProductLoaded(ProductModel productModel);

    void onErrorMessage(String error);
}
