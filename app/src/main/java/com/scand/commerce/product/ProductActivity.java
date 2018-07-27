package com.scand.commerce.product;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scand.commerce.R;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity implements ProductView, Button.OnClickListener {

    public static boolean isActive = false;

    private ProductPresenter productPresenter;

    private View v;
    private ImageView ivProductImage;
    private LinearLayout llProductBuy;
    private TextView tvPrdTitle, tvPrdPrice, tvPrdDescription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        v = getWindow().getDecorView().getRootView();

        ivProductImage = findViewById(R.id.ivProductImage);
        llProductBuy = findViewById(R.id.llProductBuy);
        llProductBuy.setOnClickListener(this);
        tvPrdTitle = findViewById(R.id.tvPrdTitle);
        tvPrdPrice = findViewById(R.id.tvPrdPrice);
        tvPrdDescription = findViewById(R.id.tvPrdDescription);

        productPresenter = new ProductPresenter(this,
                getIntent().getStringExtra("objectId"));
        productPresenter.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        productPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        isActive = false;
        if (productPresenter != null) {
            productPresenter.onStop();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    public void onProductLoaded(ProductModel productModel) {
        Picasso.get()
                .load("https://commerce-7c5d.restdb.io/media/" + productModel.getImage())
                .fit()
                .centerInside()
                .noFade()
                .into(ivProductImage);
        tvPrdTitle.setText(productModel.getTitle());
        tvPrdPrice.setText(getString(R.string.product_price, productModel.getPrice()));
        tvPrdDescription.setText(productModel.getDescription());

    }

    @Override
    public void onErrorMessage(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (llProductBuy == v) {
            Snackbar.make(v, "BUY", Snackbar.LENGTH_LONG).show();
        }
    }
}
