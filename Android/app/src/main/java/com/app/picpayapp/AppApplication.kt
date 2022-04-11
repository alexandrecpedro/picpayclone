package com.app.picpayapp

import android.app.Application
import com.app.picpayapp.di.daoModule
import com.app.picpayapp.di.repositoryModule
import com.app.picpayapp.di.serviceModule
import com.app.picpayapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    /** Apply dependency injection **/
    // All fragment/activity has its lifecycle
    override fun onCreate() {
        super.onCreate()
        // Start KOIN (and it can inject the dependencies)
        startKoin {
            // 1st parameter
            androidContext(this@AppApplication)
            // 2nd parameter
            modules(viewModelModule, serviceModule, repositoryModule, daoModule)
        }
    }
}