package com.melihkarakilinc.sondepremler.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.Repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application){

    var mainRepository: MainRepository = MainRepository()
    var DepremLiveData = MutableLiveData<List<DepremInf>>()
    var connectionLiveData = MutableLiveData<Boolean>()

    fun getDeprem() {
        if (connectionLiveData.value==true){
            DepremLiveData = mainRepository.getDepremRepository()
        }
    }
}