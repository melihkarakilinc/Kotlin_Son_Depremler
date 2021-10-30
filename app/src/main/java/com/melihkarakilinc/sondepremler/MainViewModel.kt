package com.melihkarakilinc.sondepremler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val DepremLiveData = MutableLiveData<DepremInf>()
    fun getDeprem(): DepremInf? {

        val call = DepremApi.service.getDeprem()

        call.enqueue(object : Callback<DepremInf> {
            override fun onResponse(
                call: Call<DepremInf>,
                response: Response<DepremInf>
            ) {
                if (response.isSuccessful) {
                    DepremLiveData.value = response.body()
                }
                else{

                }
            }

            override fun onFailure(call: Call<DepremInf>, t: Throwable) {
                DepremLiveData.value=null
            }

        })
        return DepremLiveData.value
    }
}