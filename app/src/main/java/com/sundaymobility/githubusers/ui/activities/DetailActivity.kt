package com.sundaymobility.githubusers.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sundaymobility.githubusers.R
import com.sundaymobility.githubusers.databinding.ActivityDetailBinding
import com.sundaymobility.githubusers.viewmodels.DetailActivityViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        detailActivityViewModel =
            ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewmodel = detailActivityViewModel

        if (intent != null && intent.extras != null) {

            val id = intent.extras!!.getString("id", "")

            detailActivityViewModel.init(id)

        }

        initObservers()


    }

    fun initObservers() {

        detailActivityViewModel.deleteEvent.observe(this, Observer {

            val value = it.getContentIfNotHandled()
            if (value != null && value) {
                finish()
            }

        })

    }


}
