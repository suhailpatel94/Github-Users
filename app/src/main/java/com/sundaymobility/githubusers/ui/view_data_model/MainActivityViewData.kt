package com.sundaymobility.githubusers.ui.view_data_model

import androidx.lifecycle.MutableLiveData


data class MainActivityViewData(
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
)