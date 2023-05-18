package uz.alpha.messenger.app

import android.app.Application
import android.content.Context
import uz.alpha.messenger.data.room.AppDatabase

class App :Application() {

    companion object{
        var   context: Context? =null
    }

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        context=this
    }
}