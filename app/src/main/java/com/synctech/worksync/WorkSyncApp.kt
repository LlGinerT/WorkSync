package com.synctech.worksync

import android.app.Application
import com.synctech.worksync.di.dataModule
import com.synctech.worksync.di.sessionModule
import com.synctech.worksync.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class WorkSyncApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WorkSyncApp)
            modules(
                sessionModule,
                dataModule,
                viewModelModule
            )
        }
    }
}