package com.mouse.lol_team

import android.app.Application
import com.mouse.lol_team.di.mainModule
import org.koin.core.context.startKoin

class LoLApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // 指定 Koin 模块
            modules(mainModule)
        }
    }

}