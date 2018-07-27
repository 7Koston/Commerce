package com.scand.commerce.products.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemModel implements Parcelable {

    public final static Parcelable.Creator<ItemModel> CREATOR = new Creator<ItemModel>() {

        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        public ItemModel[] newArray(int size) {
            return (new ItemModel[size]);
        }

    };
    @SerializedName("_id")
    @Expose
    private Object id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("image")
    @Expose
    private List<String> image = null;

    private ItemModel(Parcel in) {
        this.id = in.readValue((String.class.getClassLoader()));
        this.title = in.readString();
        this.price = in.readInt();
        in.readList(this.image, (java.lang.String.class.getClassLoader()));
    }

    public ItemModel(Object id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Object getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        if (image != null && !image.isEmpty())
            return image.get(0);
        else return "";
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeString(title);
        dest.writeInt(price);
        dest.writeList(image);
    }

    public int describeContents() {
        return 0;
    }

}