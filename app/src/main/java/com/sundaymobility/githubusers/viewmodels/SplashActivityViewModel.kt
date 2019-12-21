package com.sundaymobility.githubusers.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sundaymobility.githubusers.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class SplashActivityViewModel(application: Application) : AndroidViewModel(application) {

    val startMainActivityEvent: LiveData<Event<Boolean>> = liveData(Dispatchers.Default) {
        delay(2000)
        emit(Event(true))
    }

}