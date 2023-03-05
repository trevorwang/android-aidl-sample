package com.example.aidlserver

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceData(val value: String) : Parcelable
