package com.example.aidlserver

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceData(var value: String? = null) : Parcelable {
    fun readFromParcel(parcel: Parcel) {
        this.value = parcel.readString()
    }
}
