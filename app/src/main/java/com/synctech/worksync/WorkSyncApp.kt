package com.synctech.worksync

import android.app.Application
import android.util.Log
import com.synctech.worksync.data.WorkSessionMediator
import com.synctech.worksync.di.sessionModule
import com.synctech.worksync.domain.useCases.RestoreWorkSessionUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin


class WorkSyncApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WorkSyncApp)
            modules(sessionModule) // Aquí puedes añadir más módulos luego
        }

        // 🔽 Prueba temporal de que todo se inyecta bien
        val repoMediator: WorkSessionMediator = GlobalContext.get().get()
        val restoreUseCase: RestoreWorkSessionUseCase = GlobalContext.get().get()

        Log.d("KOIN_TEST", "Mediator inyectado: $repoMediator")
        Log.d("KOIN_TEST", "RestoreUseCase inyectado: $restoreUseCase")
    }
}