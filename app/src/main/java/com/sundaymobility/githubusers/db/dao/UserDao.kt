package com.sundaymobility.githubusers.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sundaymobility.githubusers.db.db_models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user where id=:id")
    fun get(id: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: User)

    @Delete
    fun delete(todo: User)

    @Query("DELETE FROM user WHERE id=:id")
    fun delete(id: String)

    @Update
    fun updateTodo(vararg todos: User)
}