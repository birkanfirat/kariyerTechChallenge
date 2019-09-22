package com.example.kariyertechchallenge.Presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kariyertechchallenge.Model.Session
import com.example.kariyertechchallenge.Presenter.Helper.PrefenceConnect
import com.example.kariyertechchallenge.R
import java.lang.Exception

class SessionControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_control)


        try {
            val session = PrefenceConnect.readDataToInternal("session",this) as Session
            if (!session.userName.isNullOrEmpty() || !session.userPassword.isNullOrEmpty()) {
                //Force Update için çağırıyoruz.
                val i = Intent(this, MarketListActivity::class.java)
                this.startActivity(i)
                finish()

            } else {

                val i = Intent(this, LoginActivity::class.java)
                this.startActivity(i)
                finish()

            }
        } catch (ex: Exception) {
            runOnUiThread {
                val i = Intent(this, LoginActivity::class.java)
                this.startActivity(i)
                finish()
            }
        }
    }
}
