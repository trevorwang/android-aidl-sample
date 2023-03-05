package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {
    override fun onBind(p0: Intent?): IBinder {

        return object : IRemoteService.Stub() {
            override fun basicTypes(
                anInt: Int,
                aLong: Long,
                aBoolean: Boolean,
                aFloat: Float,
                aDouble: Double,
                aString: String?
            ) {
            }

            override fun getData(): ServiceData {
                println("called in getData")
                return ServiceData("data from remote service")
            }
        }
    }

}