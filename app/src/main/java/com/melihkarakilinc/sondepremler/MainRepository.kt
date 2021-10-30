package com.melihkarakilinc.sondepremler

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    fun getDepremRepository(): MutableLiveData<DepremInf> {

        val call = DepremApi.service.getDeprem()

        var mldDepremInf = MutableLiveData<DepremInf>()

        call.enqueue(object : Callback<DepremInf> {
            override fun onResponse(
                call: Call<DepremInf>,
                response: Response<DepremInf>
            ) {


                if (response.isSuccessful) {
                   mldDepremInf.value  = response.body()!!
                } else {

                }
            }

            override fun onFailure(call: Call<DepremInf>, t: Throwable) {

            }

        })
        return mldDepremInf
    }
}