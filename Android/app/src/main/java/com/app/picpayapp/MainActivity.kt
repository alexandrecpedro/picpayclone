package com.app.picpayapp

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.picpayapp.ui.component.ComponentsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.nav_host_fragment) }
    private val componentsViewModel: ComponentsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        componentsViewModel.components.observe(this, Observer {
            it?.let { hasComponents ->
                if (hasComponents.bottomNavigation) {
                    navView.visibility = VISIBLE
                } else {
                    navView.visibility = GONE
                }
            }
        })

        navView.setupWithNavController(controller)
    }
}