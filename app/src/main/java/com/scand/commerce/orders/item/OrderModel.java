package com.scand.commerce.orders.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderModel implements Parcelable {

    public final static Parcelable.Creator<OrderModel> CREATOR = new Creator<OrderModel>() {

        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        public OrderModel[] newArray(int size) {
            return (new OrderModel[size]);
        }

    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("item_id")
    @Expose
    private String itemId;

    private OrderModel(Parcel in) {
        this.name = in.readString();
        this.phone = in.readString();
        this.itemId = in.readString();
    }

    public OrderModel(String name, String phone, String itemId) {
        this.name = name;
        this.phone = phone;
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(itemId);
    }

    public int describeContents() {
        return 0;
    }

}
