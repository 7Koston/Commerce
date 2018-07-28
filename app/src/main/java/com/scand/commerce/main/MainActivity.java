package com.scand.commerce.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.scand.commerce.R;
import com.scand.commerce.adapters.MainPagerAdapter;
import com.scand.commerce.orders.OrdersFragment;
import com.scand.commerce.products.ProductsFragment;
import com.scand.commerce.widgets.BottomNavigationViewEx;
import com.scand.commerce.widgets.NonScrollableViewPager;

public class MainActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener {

    private MainPagerAdapter mPagerAdapter;

    private View v;

    private NonScrollableViewPager nsvpMain;
    private BottomNavigationViewEx bnvMain;

    private int fragmentId;
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            fragmentId = savedInstanceState.getInt("fragmentId");
        } else {
            fragmentId = getIntent().getIntExtra("fragmentId", 0);
        }

        v = findViewById(R.id.nsvpMain);
        bnvMain = findViewById(R.id.bnvMain);
        bnvMain.getMenu().getItem(fragmentId).setChecked(true);
        setupViewPager();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (item.isChecked()) {
            return false;
        }

        if (nsvpMain != null)
            switch (id) {
                case R.id.nav_products:
                    nsvpMain.setCurrentItem(0);
                    fragmentId = 0;
                    break;
                case R.id.nav_orders:
                    nsvpMain.setCurrentItem(1);
                    fragmentId = 1;
                    break;
            }
        return true;
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            doubleBackToExitPressedOnce = true;
            Snackbar.make(v, "Press twice for exit", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

    private void setupViewPager() {
        nsvpMain = findViewById(R.id.nsvpMain);

        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setFragments(new ProductsFragment(), new OrdersFragment());
        nsvpMain.setOffscreenPageLimit(1);
        nsvpMain.setAdapter(mPagerAdapter);
        nsvpMain.setCurrentItem(fragmentId);
    }
}
