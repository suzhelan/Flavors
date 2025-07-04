package top.sacz.flavors.app

import android.app.Application
import top.sacz.flavors.config.AppConfig

class FlavorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppConfig.initApiUrl()
    }
}