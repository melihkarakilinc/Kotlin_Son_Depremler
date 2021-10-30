package com.melihkarakilinc.sondepremler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:MainViewModel
    lateinit var list:List<DepremInf>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this
        ).get(MainViewModel::class.java)


        viewModel.DepremLiveData.observe(this, Observer { depremlist->

            //list=depremlist
            Log.e("DepremList",depremlist.toString())
        })

        viewModel.getDeprem()


    }
}