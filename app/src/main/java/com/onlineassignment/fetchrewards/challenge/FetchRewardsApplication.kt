package com.onlineassignment.fetchrewards.challenge

import android.app.Application
import com.onlineassignment.fetchrewards.challenge.di.networkModule
import com.onlineassignment.fetchrewards.challenge.di.repositoryModule
import com.onlineassignment.fetchrewards.challenge.di.useCaseModule
import com.onlineassignment.fetchrewards.challenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FetchRewardsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@FetchRewardsApplication)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}
