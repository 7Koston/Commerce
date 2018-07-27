package com.scand.commerce.products;


import com.scand.commerce.products.item.ItemModel;

import java.util.ArrayList;

public interface ProductsView {

    void onProductsLoaded(ArrayList<ItemModel> itemModels);

    void onItemClicked(ItemModel model, int position);

    void onErrorMessage(String error);
}
