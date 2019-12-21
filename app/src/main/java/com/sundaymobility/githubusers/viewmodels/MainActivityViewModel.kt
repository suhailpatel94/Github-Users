package com.sundaymobility.githubusers.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinprac.utils.data_models.Result
import com.sundaymobility.githubusers.db.db_models.User
import com.sundaymobility.githubusers.repository.UserRepository
import com.sundaymobility.githubusers.ui.view_data_model.MainActivityViewData
import com.sundaymobility.githubusers.ui.view_data_model.UserListItemViewData
import com.sundaymobility.githubusers.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val mainActivityViewData = MainActivityViewData()
    var swipeEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    var addUserEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()

    private val userRepository = UserRepository(application)


    fun fetchAllUsers(): LiveData<Result<List<User>>> {
        mainActivityViewData.loading.value = true
        return userRepository.fetchAllUsers()
    }

    fun loadData() {
        mainActivityViewData.loading.value = true
        swipeEvent.value = Event(true)
    }

    fun addUser() {
        addUserEvent.value = Event(true)
    }

    fun saveUser(user: User) {

        viewModelScope.launch(Dispatchers.IO) {

            userRepository.insert(user)
        }
    }

    private val userList: MutableLiveData<List<User>> = MutableLiveData()


    val userListViewData: LiveData<List<UserListItemViewData>> =
        Transformations.switchMap(userList) {
            val liveData: MutableLiveData<List<UserListItemViewData>> =
                MutableLiveData(userListToViewData(it))
            liveData
        }


    fun onDataReceived(result: Result<List<User>>) {

        when (result.status) {

            Result.Status.SUCCESS -> {

                mainActivityViewData.loading.value = false
                if (result.data != null) {
                    userList.value = result.data
                }

            }
            Result.Status.LOADING -> {
                mainActivityViewData.loading.value = true
            }
            Result.Status.ERROR -> {
                mainActivityViewData.loading.value = false
            }
            Result.Status.UNSUCCESSFUL -> {
                mainActivityViewData.loading.value = false
            }
        }

    }

    fun userListToViewData(userList: List<User>): List<UserListItemViewData> {
        val userListViewData = mutableListOf<UserListItemViewData>()
        for (user in userList) {
            val viewData = UserListItemViewData()

            viewData.id = user.id.toString()
            viewData.name = user.login
            viewData.image_url = user.avatarUrl
            viewData.type = user.type

            userListViewData.add(viewData)
        }

        return userListViewData
    }

}