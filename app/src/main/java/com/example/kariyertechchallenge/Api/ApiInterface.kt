package com.example.kariyertechchallenge.Api

import com.example.kariyertechchallenge.Model.MarketingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    abstract fun getMarketingList(@Url url: String) : Call<List<MarketingModel>>

}