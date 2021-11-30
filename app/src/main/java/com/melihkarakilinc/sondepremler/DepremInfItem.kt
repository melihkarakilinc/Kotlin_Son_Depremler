package com.melihkarakilinc.sondepremler

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "depremRDB")
data class DepremInfItem(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val boylam: String,
    val derinlik: String,
    val enlem: String,
    val saat: String,
    val siddet: String,
    val tarih: String,
    val tip: String,
    val yer: String
)