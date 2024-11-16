package com.jahangirjadi.cryptotracker

import android.app.Application
import com.jahangirjadi.cryptotracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CryptoTrackerApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CryptoTrackerApp)
            androidLogger()

            modules(appModule)
        }

    }
}