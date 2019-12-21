package com.sundaymobility.githubusers.repository

import android.app.Application
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.kotlinprac.api.ApiClient
import com.example.kotlinprac.utils.data_models.Result
import com.sundaymobility.githubusers.api.ApiService
import com.sundaymobility.githubusers.db.AppDatabase
import kotlinx.coroutines.Dispatchers

class UserRepository(private val application: Application) {

    private val apiService: ApiService
    private val userRemoteDataSource: UserRemoteDataSource

    init {
        apiService = ApiClient.getClient(application)
        userRemoteDataSource = UserRemoteDataSource(apiService)
    }


    fun fetchAllUsers() = liveData(Dispatchers.IO) {

        val loadingSource =
            AppDatabase.getDatabase(application).userDao().getAll().map { Result.loading(it) }

        val successSource =
            AppDatabase.getDatabase(application).userDao().getAll().map { Result.success(it) }

        val disposableLoading = emitSource(loadingSource)

        val responseStatus = userRemoteDataSource.fetchUsers()


        when (responseStatus.status) {

            Result.Status.SUCCESS -> {
                disposableLoading.dispose()
                AppDatabase.getDatabase(application).userDao().insertAll(responseStatus.data!!)
                emitSource(successSource)
            }
            Result.Status.ERROR -> {
                emit(Result.error(responseStatus.message!!))
                emitSource(successSource)
            }
            Result.Status.UNSUCCESSFUL -> {
                emit(Result.unsuccessful(responseStatus.status_code, responseStatus.message))
                emitSource(successSource)
            }


            else -> {
                disposableLoading.dispose()
                AppDatabase.getDatabase(application).userDao().insertAll(responseStatus.data!!)
                emitSource(successSource)
            }
        }

    }


}