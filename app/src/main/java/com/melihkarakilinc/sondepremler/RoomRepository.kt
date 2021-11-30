package com.melihkarakilinc.sondepremler

import androidx.lifecycle.LiveData

class RoomRepository(private val depremDao: DepremDao) {

    val getAllData: LiveData<List<DepremInfItem>> = depremDao.getRoomData()

    suspend fun insertData(depremInfItem: DepremInfItem) {
        depremDao.insertRoomData(depremInfItem)
    }

    suspend fun deleteData(depremInfItem: DepremInfItem){
        depremDao.deleteRoomData(depremInfItem)
    }
}