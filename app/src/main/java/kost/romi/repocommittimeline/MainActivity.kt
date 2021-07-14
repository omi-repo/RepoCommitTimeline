package kost.romi.repocommittimeline

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

//        findViewById<TextView>(R.id.build_config_test).text =
//            "${BuildConfig.GithubClientId}\n${BuildConfig.GithubClientSecret}\n${BuildConfig.Token}"
    }
}