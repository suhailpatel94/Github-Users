package com.sundaymobility.githubusers.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sundaymobility.githubusers.R
import com.sundaymobility.githubusers.databinding.ActivityMainBinding
import com.sundaymobility.githubusers.ui.adapters.UserAdapter
import com.sundaymobility.githubusers.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var context: Context
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this


        initUserList()
        initObservers()


    }

    private fun initUserList() {

        userAdapter = UserAdapter()

        userAdapter.initCallBack {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        val RVLayoutManager = LinearLayoutManager(context)
        val recyclerView = binding.userList
        recyclerView.layoutManager = RVLayoutManager
        recyclerView.adapter = userAdapter
        recyclerView.setHasFixedSize(true)


    }


    fun initObservers() {

        mainActivityViewModel.fetchAllUsers().observe(this, Observer {
            mainActivityViewModel.onDataReceived(it)
        })

        mainActivityViewModel.userListViewData.observe(this, Observer {
            userAdapter.submitList(it)
        })
    }
}
