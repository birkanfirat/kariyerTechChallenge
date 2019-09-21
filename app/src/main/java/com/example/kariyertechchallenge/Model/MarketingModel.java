package com.example.kariyertechchallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarketingModel {
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("month")
    @Expose
    public String month;
    @SerializedName("marketName")
    @Expose
    public String marketName;
    @SerializedName("orderName")
    @Expose
    public String orderName;
    @SerializedName("productPrice")
    @Expose
    public Float productPrice;
    @SerializedName("productState")
    @Expose
    public String productState;
    @SerializedName("productDetail")
    @Expose
    public ProductDetail productDetail;

}

