package com.onwd.challenge

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.onwd.devices.DeviceInteractorStub
import com.onwd.challenge.ListAdapter.ItemsViewModel
import com.onwd.challenge.fragment.DeviceSearch

public class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, DeviceSearch())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }




}