package com.example.kariyertechchallenge.Presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kariyertechchallenge.Model.Session
import com.example.kariyertechchallenge.Presenter.Helper.PrefenceConnect
import com.example.kariyertechchallenge.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            controller()
        }
    }

    fun controller(){

        if(username.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
            Toast.makeText(this,"Kullanıcı Adı veya Şifre Boş Olamaz",Toast.LENGTH_LONG).show()
            return
        }
        if(username.text.toString() !="kariyer" || password.text.toString()!="2019ADev"){
            Toast.makeText(this,"Kullanıcı Adı veya Şifre Yanlış",Toast.LENGTH_LONG).show()
            return
        }

        goToMarketting()

    }

    fun goToMarketting(){
        if(switchRememberMe.isChecked){
            val session = Session()
            session.userName = username.text.toString()
            session.userPassword = password.text.toString()

            PrefenceConnect.writeDataToInternal("session", session,this)
        }
        val i = Intent(this, MarketListActivity::class.java)
        this.startActivity(i)

    }
}
