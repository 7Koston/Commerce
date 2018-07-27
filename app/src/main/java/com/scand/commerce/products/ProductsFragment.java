package com.scand.commerce.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scand.commerce.R;
import com.scand.commerce.product.ProductActivity;
import com.scand.commerce.products.item.ItemAdapter;
import com.scand.commerce.products.item.ItemModel;
import com.scand.commerce.widgets.TintedProgressBar;

import java.util.ArrayList;

public class ProductsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        ProductsView {

    private ProductsPresenter mPresenter;

    private ItemAdapter mAdapter;

    private View v;
    private RecyclerView rvProducts;
    private TintedProgressBar pbProducts;
    private SwipeRefreshLayout srlProducts;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v = inflater.inflate(R.layout.fragment_products, container, false);

        Context mContext = v.getContext();

        rvProducts = v.findViewById(R.id.rvProducts);
        pbProducts = v.findViewById(R.id.pbProducts);
        srlProducts = v.findViewById(R.id.srlProducts);

        mPresenter = new ProductsPresenter(this);

        mAdapter = new ItemAdapter(mPresenter);

        rvProducts.setLayoutManager(
                new LinearLayoutManager(mContext));
        rvProducts.setAdapter(mAdapter);
        srlProducts.setOnRefreshListener(this);

        mPresenter.onCreate(savedInstanceState);
        return v;
    }

    @Override
    public void onRefresh() {
        srlProducts.setRefreshing(true);
        mPresenter.refresh();
        mAdapter.notifyItemRangeRemoved(0, mPresenter.clearList());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    public void onProductsLoaded(ArrayList<ItemModel> models) {
        pbProducts.setVisibility(View.GONE);
        srlProducts.setRefreshing(false);
        mAdapter.notifyItemRangeInserted(0, models.size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(ItemModel model, int position) {
        if (!ProductActivity.isActive) {

            Intent intent = new Intent(getContext(), ProductActivity.class);
            intent.putExtra("objectId", (String) model.getId());

            startActivity(intent);
        }
    }

    @Override
    public void onForceRefresh() {
        onRefresh();
    }

    @Override
    public void onErrorMessage(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();
        pbProducts.setVisibility(View.GONE);
        srlProducts.setRefreshing(false);
    }
}
