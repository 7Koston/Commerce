package com.scand.commerce.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductModel implements Parcelable {

    public final static Parcelable.Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        public ProductModel[] newArray(int size) {
            return (new ProductModel[size]);
        }

    };
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private List<String> image = null;

    private ProductModel(Parcel in) {
        this.price = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        in.readList(this.image, (java.lang.String.class.getClassLoader()));
    }

    public ProductModel(String description, String title, int price) {
        this.description = description;
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeList(image);
    }

    public int describeContents() {
        return 0;
    }

}
