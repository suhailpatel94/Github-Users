package com.sundaymobility.githubusers.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.sundaymobility.githubusers.db.AppDatabase
import com.sundaymobility.githubusers.db.db_models.User
import com.sundaymobility.githubusers.repository.UserRepository
import com.sundaymobility.githubusers.ui.view_data_model.DetailActivityViewData
import com.sundaymobility.githubusers.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    val detailActivityViewData = DetailActivityViewData()
    val deleteEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    var id: String = ""

    fun fetchData() = liveData(Dispatchers.IO) {
        emitSource(userRepository.fetch(id))
    }


    fun init(id: String) {
        this.id = id
    }


    fun onDataFetched(user: User) {

        detailActivityViewData.avatar_url.value = user.avatarUrl


    }

    fun delete() {
        viewModelScope.launch(Dispatchers.Default) {
            userRepository.delete(id)
            deleteEvent.postValue(Event(true))
        }
    }

}