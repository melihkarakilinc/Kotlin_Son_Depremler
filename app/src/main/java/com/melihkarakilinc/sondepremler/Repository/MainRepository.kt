package com.melihkarakilinc.sondepremler.Repository

import androidx.lifecycle.MutableLiveData
import com.melihkarakilinc.sondepremler.Model.DepremInf
import kotlinx.coroutines.*

class MainRepository {


    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String): MutableLiveData<String> {
        val error = MutableLiveData<String>()
        error.value = message
        return error
    }

    fun getDepremRepository(): MutableLiveData<List<DepremInf>> {
        val mldDepremInf = MutableLiveData<List<DepremInf>>()
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = DepremApi.service.getDeprem()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    mldDepremInf.value = response.body()!!
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
        return mldDepremInf
    }
}