package com.sundaymobility.githubusers.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sundaymobility.githubusers.repository.UserRepository
import com.sundaymobility.githubusers.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)
    val deleteEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    var id: String = ""

    fun init(id: String) {
        this.id = id
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.Default) {
            userRepository.delete(id)
            deleteEvent.postValue(Event(true))
        }
    }

}