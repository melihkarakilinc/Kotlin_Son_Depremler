package com.melihkarakilinc.sondepremler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this
        ).get(MainViewModel::class.java)

        viewModel.getDeprem()

        viewModel.DepremLiveData.observe(this, Observer { depremlist->

            Log.e("DepremList",depremlist.toString())
        })

    }
}