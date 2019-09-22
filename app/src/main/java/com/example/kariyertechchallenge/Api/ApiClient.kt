package com.example.kariyertechchallenge.Api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    //Retrofit Call
    companion object Factory {
        private var retrofit: Retrofit? = null
        private val Base_Url = "http://kariyertechchallenge.mockable.io"
        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient())
                    .build()
                return retrofit as Retrofit
            }
            return retrofit as Retrofit
        }
    }
}