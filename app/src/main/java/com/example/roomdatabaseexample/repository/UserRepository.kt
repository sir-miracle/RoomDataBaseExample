package com.example.roomdatabaseexample.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.data.IUserDao
import com.example.roomdatabaseexample.model.User


//the repository class abstracts access to multiple data sources
class UserRepository(private val userDao: IUserDao){

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }
}