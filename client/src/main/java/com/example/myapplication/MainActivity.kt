package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.aidlserver.IRemoteService
import com.example.aidlserver.ServiceData
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            iRemoteService?.data?.let {

                Snackbar.make(view, it.value ?: "", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

            val data = ServiceData("hello variable in")
            iRemoteService?.updateDataIn(data)
            println(data.value)


            val data2 = ServiceData("hello variable out")
            iRemoteService?.updateDataOut(data2)
            println(data2.value)



            val data3 = ServiceData("hello variable Inout")
            iRemoteService?.updateDataInOut(data3)
            println(data3.value)
        }
    }

    override fun onStart() {
        super.onStart()
        bindService()
    }

    override fun onStop() {
        super.onStop()
        iRemoteService = null
    }

    private fun bindService() {
        Intent()
            .apply {
                action = "com.example.aidlserver.RemoteService"
                `package` = "com.example.aidlserver"
            }
            .also {
                bindService(it, mServiceConnection, BIND_AUTO_CREATE)
            }
    }

    private var iRemoteService: IRemoteService? = null
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            iRemoteService = IRemoteService.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            iRemoteService = null
        }
    }


}