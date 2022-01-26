package com.melihkarakilinc.sondepremler.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.melihkarakilinc.sondepremler.Model.DepremInf
import kotlinx.coroutines.*

class MainRepository {


    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

    }


    fun getDepremRepository(): MutableLiveData<List<DepremInf>> {
        val mldDepremInf = MutableLiveData<List<DepremInf>>()
        try {
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = DepremApi.service.getDeprem()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        mldDepremInf.value = response.body()!!
                    } else {

                    }
                }
            }
        } catch (e: IllegalStateException) {
            mldDepremInf.value = null
            Log.e("Hata", e.toString())
        }
        return mldDepremInf
    }
}