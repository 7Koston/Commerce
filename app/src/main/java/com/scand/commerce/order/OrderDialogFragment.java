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

public class OrderDialogFragment extends DialogFragment implements Button.OnClickListener, OrderDialogView {

    private OrderDialogPresenter mPresenter;
    private OrderDialogStateListener mListener;

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

        return dialog;
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
        mListener.onFinishDialog(response);
        dismiss();
    }

    @Override
    public OrderModel onOrderModelRequested(String objectId) {
        return new OrderModel(tietOrderYourName.getText().toString(),
                tietOrderYourPhone.getText().toString(), objectId);
    }

    @Override
    public void onErrorMessage(String error) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show();
    }

    public void setOrderDialogStateListener(OrderDialogStateListener listener) {
        this.mListener = listener;
    }

    public interface OrderDialogStateListener {
        void onFinishDialog(String msg);
    }
}