package com.niu.mystudy

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.niu.mystudy.databinding.ActivityMainBinding
import com.niu.mystudy.viewmodel.WelcomeViewModel

class MainActivity : AppCompatActivity() {

    var myData : MainData = MainData("")
    val handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        contentView.data = myData

        val model = ViewModelProviders.of(this)[WelcomeViewModel::class.java]
        model.getInfo().observe(this, Observer { x -> if(x != null){
            myData.info = x

            handler.postDelayed(Runnable {
                val intent = Intent()
                intent.setClass(this, CategoryListActivity::class.java!!)
                startActivity(intent)

                finish()

            }, 2000)

        } })
    }

    data class MainData(var info : String)
}

