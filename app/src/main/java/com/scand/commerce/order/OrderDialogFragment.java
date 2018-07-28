package com.scand.commerce.order;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.scand.commerce.R;
import com.scand.commerce.orders.item.OrderModel;
import com.scand.commerce.product.ProductActivity;

public class OrderDialogFragment extends DialogFragment implements Button.OnClickListener, OrderDialogView {

    private OrderDialogPresenter mPresenter;

    private View v;

    private TextInputEditText tietOrderYourName, tietOrderYourPhone;
    private Button btOrderBuy;

    public OrderDialogFragment() {
    }

    public static OrderDialogFragment newInstance(String id) {
        OrderDialogFragment frag = new OrderDialogFragment();
        Bundle args = new Bundle();
        args.putString("objectId", id);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null)
            window.requestFeature(Window.FEATURE_NO_TITLE);

        Bundle args = getArguments();
        if (args != null)
            mPresenter = new OrderDialogPresenter(this, args.getString("objectId"));
        else
            mPresenter = new OrderDialogPresenter(this, "");
        mPresenter.onCreate(savedInstanceState);

        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialog_order, container);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tietOrderYourName = view.findViewById(R.id.tietOrderYourName);
        tietOrderYourPhone = view.findViewById(R.id.tietOrderYourPhone);
        btOrderBuy = view.findViewById(R.id.btOrderBuy);

        btOrderBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btOrderBuy) {
            mPresenter.sendOrder();
        }
    }

    @Override
    public void onPostFinished(String response) {
        ProductActivity parent = (ProductActivity) getActivity();
        if (parent != null)
            parent.onFinishDialog(response);
        dismiss();
    }

    @Override
    public OrderModel onOrderModelRequested(String objectId) {
        String name, phone, id;
        name = tietOrderYourName.getText().toString();
        phone = tietOrderYourPhone.getText().toString();
        id = objectId;
        if (!name.isEmpty() && !phone.isEmpty() && !id.isEmpty())
            return new OrderModel(name, phone, id);
        else return null;
    }

    @Override
    public void onErrorMessage(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();
    }

    public interface OrderDialogStateListener {
        void onFinishDialog(String msg);
    }
}
