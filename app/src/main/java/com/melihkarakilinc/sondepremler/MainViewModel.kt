package com.melihkarakilinc.sondepremler

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){

    var mainRepository: MainRepository = MainRepository()
    var DepremLiveData = MutableLiveData<DepremInf>()
    var connectionLiveData = MutableLiveData<Boolean>()

    fun getDeprem() {
        if (connectionLiveData.value==true){
            DepremLiveData = mainRepository.getDepremRepository()
        }
    }
}