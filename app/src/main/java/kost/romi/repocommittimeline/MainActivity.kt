package kost.romi.repocommittimeline

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint


/**
 * TODO: use setTheme and recreate in activity to change and update theme.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setTheme(R.)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

//        findViewById<TextView>(R.id.build_config_test).text =
//            "${BuildConfig.GithubClientId}\n${BuildConfig.GithubClientSecret}\n${BuildConfig.Token}"
    }
}