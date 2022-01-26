package com.melihkarakilinc.sondepremler.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.Repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application){

    var mainRepository: MainRepository = MainRepository()
    var DepremLiveData = MutableLiveData<List<DepremInf>>()
    var progressLiveData = MutableLiveData<Boolean>()

    fun getDeprem() {
        DepremLiveData = mainRepository.getDepremRepository()
        progressLiveData.value = DepremLiveData.value.isNullOrEmpty()
    }
}