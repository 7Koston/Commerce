package com.scand.commerce.products.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scand.commerce.R;
import com.scand.commerce.widgets.AspectRatioImageView;
import com.squareup.picasso.Picasso;

public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemCallBack,
        View.OnClickListener {

    private final TextView tvProductPrice;
    private final TextView tvProductTitle;
    private final AspectRatioImageView arivItemProduct;

    private final ItemAdapter.OnItemClickListener onItemClickListener;

    ItemViewHolder(@NonNull View view, ItemAdapter.OnItemClickListener callBack) {
        super(view);
        onItemClickListener = callBack;
        tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
        tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        arivItemProduct = itemView.findViewById(R.id.arivItemProduct);
        CardView cvItemProduct = itemView.findViewById(R.id.cvItemProduct);
        cvItemProduct.setOnClickListener(this);
    }

    ItemCallBack getOnItemCallBack() {
        return this;
    }

    @Override
    public void setTitle(String title) {
        tvProductTitle.setText(title);
    }

    @Override
    public void setPrice(int price) {
        tvProductPrice.setText(String.valueOf(price));
    }

    @Override
    public void setImageUrl(String url) {
        if (url != null && !url.isEmpty())
            Picasso.get()
                    .load("https://commerce-7c5d.restdb.io/media/" + url)
                    .fit()
                    .centerCrop()
                    .noFade()
                    .into(arivItemProduct);
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemCLick(getAdapterPosition());
    }
}
