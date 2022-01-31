package com.example.roomdatabaseexample.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabaseexample.model.User


//the Dao contains all the methods used to access the database
//we create all the necessary queries here
@Dao
interface IUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table ORDER BY id ASC")//read everything from the user_table
                                                            // in the database
    fun readAllData(): LiveData<List<User>>






}