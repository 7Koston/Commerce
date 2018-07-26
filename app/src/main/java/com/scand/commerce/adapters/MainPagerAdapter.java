package com.scand.commerce.adapters;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.scand.commerce.orders.OrdersFragment;
import com.scand.commerce.products.ProductsFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final Fragment[] mFragmentList = new Fragment[2];

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setFragments(Fragment f1, Fragment f2) {
        mFragmentList[0] = f1;
        mFragmentList[1] = f2;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList[position];
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object ret = super.instantiateItem(container, position);
        mFragmentList[position] = (Fragment) ret;
        return ret;
    }

    @Override
    public int getCount() {
        return mFragmentList.length;
    }

    @Override
    public Parcelable saveState() {
        Bundle bundle = (Bundle) super.saveState();
        if (bundle != null)
            bundle.putParcelableArray("states", null); // Never maintain any states from the base class, just null it out
        return bundle;
    }

    public ProductsFragment getProductsFragment() {
        return (ProductsFragment) mFragmentList[0];
    }

    public OrdersFragment getOrdersFragment() {
        return (OrdersFragment) mFragmentList[1];
    }
}