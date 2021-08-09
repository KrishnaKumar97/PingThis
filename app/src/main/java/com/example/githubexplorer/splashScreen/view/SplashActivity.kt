package com.example.githubexplorer.splashScreen.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.githubexplorer.R
import com.example.githubexplorer.repositoryScreen.application.view.RepositoryActivity


//Splash activity
class SplashActivity : AppCompatActivity() {
    /**
     * @param savedInstanceState
     * Passing the Layout Resource to the Base Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        timeDelayForSplashScreen()
    }


    /**
     * TimeDelay for the Splash Screen
     */
    private fun timeDelayForSplashScreen() {
        val logoHandler = Handler()
        val logoRunnable = Runnable {
            startActivity(Intent(this,
                RepositoryActivity::class.java))
        }
        logoHandler.postDelayed(logoRunnable, 3000)
    }

    /**
     * Function called on resume
     */
    override fun onResume() {
        super.onResume()
        timeDelayForSplashScreen()
    }
}