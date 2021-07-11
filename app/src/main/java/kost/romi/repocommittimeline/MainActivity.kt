package kost.romi.repocommittimeline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<TextView>(R.id.build_config_test).text =
//            "${BuildConfig.GithubClientId}\n${BuildConfig.GithubClientSecret}"
    }
}