package by.lukashenko.mvp.example

import android.app.Application
import com.activeandroid.ActiveAndroid

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ActiveAndroid.initialize(this)
    }
}