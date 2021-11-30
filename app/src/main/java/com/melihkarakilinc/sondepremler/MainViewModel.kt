package com.melihkarakilinc.sondepremler

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val depremDao = AppDatabase.getDatabase(application).depremDao()
    private var repository: RoomRepository = RoomRepository(depremDao)
    val getAllData: LiveData<List<DepremInfItem>> = repository.getAllData

    fun insertData(depremInfItem: DepremInfItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(depremInfItem)
        }
    }

    var mainRepository: MainRepository = MainRepository()
    var DepremLiveData = MutableLiveData<DepremInf>()

    fun getDeprem() {
        DepremLiveData = mainRepository.getDepremRepository()
    }
}