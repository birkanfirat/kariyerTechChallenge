package com.example.kariyertechchallenge.Presenter

import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kariyertechchallenge.Api.ApiClient
import com.example.kariyertechchallenge.Api.ApiInterface
import com.example.kariyertechchallenge.Api.Constants
import com.example.kariyertechchallenge.Model.MarketingModel
import com.example.kariyertechchallenge.Presenter.Adapter.MarketListAdapter
import com.example.kariyertechchallenge.Presenter.Helper.PrefenceConnect
import com.example.kariyertechchallenge.R
import kotlinx.android.synthetic.main.activity_marketting.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MarketListActivity : AppCompatActivity() {
    lateinit var apiInterface: ApiInterface
    var markettingList: ArrayList<MarketingModel> = ArrayList<MarketingModel>()
    lateinit var adapter: MarketListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketting)
        //interface initialize
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        //adapter initialize
        adapter = MarketListAdapter(this, markettingList)

        markettingListView.setAdapter(adapter)

        init()


        btnLogOut.setOnClickListener {
            //Çıkış butonuna tıklandığında çağırılır.
            closeApp()
        }

    }

    override fun onBackPressed() {
        closeApp()

    }

    fun init() {
        //Service Call
        val getMarketing = apiInterface.getMarketingList(Constants.BASE_URL)
        getMarketing.enqueue(object : Callback<List<MarketingModel>> {

            override fun onFailure(call: Call<List<MarketingModel>>, t: Throwable) {
                Log.d("",t.message)

            }

            override fun onResponse(call: Call<List<MarketingModel>>, response: Response<List<MarketingModel>>) {
                Log.d("",response.body().toString())
                if(!response.body().isNullOrEmpty()) {
                    //Dönen responsu adaptera setledik.
                    adapter.markettingList = response.body()!!
                    adapter.notifyDataSetChanged()
                }


            }
        })

    }
    fun closeApp(){
        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(this)
        }

        builder.setTitle("Çıkış")
            .setMessage("Çıkış yapmak istiyor musunuz?")
            .setPositiveButton("İstiyorum") { dialog, which ->
                //Çıkış yapıldığında session'ı siliyoruz
                PrefenceConnect.deleteDataToInternal("session",this)
                //Tüm aktivitileri bitiriyoruz.
               finishAffinity()

            }
            .setNegativeButton("İptal") { dialog, which ->
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()

    }

}
