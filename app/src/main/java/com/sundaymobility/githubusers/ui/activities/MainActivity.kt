package com.sundaymobility.githubusers.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinprac.utils.data_models.Result
import com.sundaymobility.githubusers.R
import com.sundaymobility.githubusers.databinding.ActivityMainBinding
import com.sundaymobility.githubusers.databinding.AddUserDialogBinding
import com.sundaymobility.githubusers.db.db_models.User
import com.sundaymobility.githubusers.ui.adapters.UserAdapter
import com.sundaymobility.githubusers.viewmodels.MainActivityViewModel
import java.util.*

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
        binding.viewmodel = mainActivityViewModel
        binding.data = mainActivityViewModel.mainActivityViewData


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


    var apiObserver: Observer<Result<List<User>>> = Observer {
        mainActivityViewModel.onDataReceived(it)
    }

    fun initObservers() {

        mainActivityViewModel.addUserEvent.observe(this, Observer {

            val value = it.getContentIfNotHandled()
            if (value != null && value) {
                showAddUserDialog()
            }

        })
        mainActivityViewModel.fetchAllUsers().observe(this, Observer {
            mainActivityViewModel.onDataReceived(it)
        })

        mainActivityViewModel.userListViewData.observe(this, Observer {
            userAdapter.submitList(it)
        })

        mainActivityViewModel.swipeEvent.observe(this, Observer {
            val value = it.getContentIfNotHandled()
            if (value != null && value) {
                mainActivityViewModel.fetchAllUsers().removeObserver(apiObserver)
                mainActivityViewModel.fetchAllUsers().observe(this, apiObserver)
            }
        })
    }

    fun showAddUserDialog() {


        val add_user_binding: AddUserDialogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.add_user_dialog, null, false)

        add_user_binding.executePendingBindings()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add User")
        builder.setView(add_user_binding.root)

        builder.setPositiveButton("Add") { dialogInterface, which ->

            val name = add_user_binding.nameField.text.toString()
            val type = add_user_binding.typeField.text.toString()

            if (name.isBlank()) {
                shortToast("Name is reqired")
            } else if (type.isBlank()) {
                shortToast("Type is reqired")
            } else {
                val user = User(
                    UUID.randomUUID().toString(),
                    "",
                    name,
                    type
                )

                mainActivityViewModel.saveUser(user)
            }

        }

        builder.setNegativeButton("Cancel") { dialogInterface, which ->

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    fun shortToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
