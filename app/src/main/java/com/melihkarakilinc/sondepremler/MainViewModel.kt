package com.melihkarakilinc.sondepremler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var mainRepository:MainRepository= MainRepository()
    var DepremLiveData = MutableLiveData<DepremInf>()
    fun getDeprem(): DepremInf? {
        DepremLiveData= mainRepository.getDepremRepository()
        return DepremLiveData.value
    }
}