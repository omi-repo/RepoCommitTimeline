package kost.romi.repocommittimeline

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

//        // Colorful, for theming. (https://github.com/garretyoder/Colorful)
//        val defaults: Defaults = Defaults(
//            primaryColor = ThemeColor.GREEN,
//            accentColor = ThemeColor.BLUE,
//            useDarkTheme = false,
//            translucent = false
//        )
//        initColorful(this, defaults)
    }
}