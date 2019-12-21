package com.sundaymobility.githubusers.repository

import com.sundaymobility.githubusers.api.ApiService
import com.sundaymobility.githubusers.api.BaseDataSource

class UserRemoteDataSource(private val service: ApiService) : BaseDataSource() {

    suspend fun fetchUsers() = getResult { service.users() }

}
