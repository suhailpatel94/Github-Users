package com.sundaymobility.githubusers.db.db_models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@Entity(tableName = "user")
@JsonClass(generateAdapter = true)
data class User(

    @NonNull
    @PrimaryKey
    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "avatar_url")
    val avatarUrl: String = "",

    @Json(name = "login")
    val login: String = "",

    @Json(name = "type")
    val type: String = ""

)