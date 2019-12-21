package com.sundaymobility.githubusers.api

import com.sundaymobility.githubusers.db.db_models.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun users(): Response<List<User>>


}