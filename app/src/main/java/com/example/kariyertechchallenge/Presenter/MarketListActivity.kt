package com.example.kariyertechchallenge.Presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kariyertechchallenge.Api.ApiClient
import com.example.kariyertechchallenge.Api.ApiInterface
import com.example.kariyertechchallenge.Api.Constants
import com.example.kariyertechchallenge.Model.MarketingModel
import com.example.kariyertechchallenge.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketListActivity : AppCompatActivity() {
    lateinit var apiInterface: ApiInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketting)

        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        init()

    }

    fun init() {

        val getMarketing = apiInterface.getMarketingList(Constants.BASE_URL)
        getMarketing.enqueue(object : Callback<List<MarketingModel>> {

            override fun onFailure(call: Call<List<MarketingModel>>, t: Throwable) {
                Log.d("",t.message)

            }

            override fun onResponse(call: Call<List<MarketingModel>>, response: Response<List<MarketingModel>>) {
                Log.d("",response.body().toString())


            }
        })

    }

}
