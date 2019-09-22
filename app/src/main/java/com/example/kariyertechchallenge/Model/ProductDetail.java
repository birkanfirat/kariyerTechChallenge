package com.example.kariyertechchallenge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail {

        @SerializedName("orderDetail")
        @Expose
        public String orderDetail;
        @SerializedName("summaryPrice")
        @Expose
        public Float summaryPrice;


}
