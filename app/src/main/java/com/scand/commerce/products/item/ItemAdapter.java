package com.scand.commerce.products.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.scand.commerce.R;
import com.scand.commerce.products.ProductsPresenter;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final ProductsPresenter downloadsPresenter;
    private final OnItemClickListener onItemClickListener;

    public ItemAdapter(ProductsPresenter productsPresenter) {
        this.downloadsPresenter = productsPresenter;
        this.onItemClickListener = productsPresenter.getOnItemClickListener();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products, parent, false),
                onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        downloadsPresenter.onBindVolumeFileViewAtPosition(position, holder.getOnItemCallBack());
    }

    @Override
    public int getItemCount() {
        return downloadsPresenter.getVolumeFilesCount();
    }


    public interface OnItemClickListener {
        void onItemCLick(int position);
    }
}
