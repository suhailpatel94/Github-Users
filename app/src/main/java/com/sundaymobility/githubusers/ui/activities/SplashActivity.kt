package com.sundaymobility.githubusers.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sundaymobility.githubusers.R
import com.sundaymobility.githubusers.viewmodels.SplashActivityViewModel

class SplashActivity : AppCompatActivity() {


    private lateinit var splashActivityViewModel: SplashActivityViewModel
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.splash)
        super.onCreate(savedInstanceState)

        context = this
        splashActivityViewModel =
            ViewModelProviders.of(this).get(SplashActivityViewModel::class.java)

        initObservers()
    }

    fun initObservers() {

        splashActivityViewModel.startMainActivityEvent.observe(this, Observer {

            val value = it.getContentIfNotHandled()
            if (value != null && value) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        })

    }


}
