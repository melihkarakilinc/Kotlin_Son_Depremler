package com.melihkarakilinc.sondepremler

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DepremDao {
    @Query("SELECT*FROM depremRDB")
    fun getRoomData(): LiveData<List<DepremInfItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRoomData(depremInfItem: DepremInfItem)

    @Delete
    suspend fun deleteRoomData(depremInfItem: DepremInfItem)
}