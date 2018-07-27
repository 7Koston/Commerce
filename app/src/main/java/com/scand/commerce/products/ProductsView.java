package com.scand.commerce.products;


import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

public interface ProductsView {

    void onProductsLoaded(ArrayList<ItemModel> volumeFileModels);

    void onItemClicked(ItemModel model, int position);

    void onForceRefresh();

    void onErrorMessage(String error);
}
