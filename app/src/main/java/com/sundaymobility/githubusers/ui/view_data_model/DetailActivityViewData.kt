package com.sundaymobility.githubusers.ui.view_data_model

import androidx.lifecycle.MutableLiveData


data class DetailActivityViewData(
    val avatar_url: MutableLiveData<String> = MutableLiveData("")
)