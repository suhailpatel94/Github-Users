package com.sundaymobility.githubusers.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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



    }



}
