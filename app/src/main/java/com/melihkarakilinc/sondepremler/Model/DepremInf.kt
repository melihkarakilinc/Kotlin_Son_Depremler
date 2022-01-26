package com.melihkarakilinc.sondepremler.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepremInf(
    val boylam: String,
    val derinlik: String,
    val enlem: String,
    val saat: String,
    val siddet: String,
    val tarih: String,
    val tip: String,
    val yer: String
): Parcelable {}