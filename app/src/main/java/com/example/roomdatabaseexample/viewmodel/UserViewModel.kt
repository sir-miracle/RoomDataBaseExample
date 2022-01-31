package com.example.roomdatabaseexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseexample.data.UserDatabase
import com.example.roomdatabaseexample.repository.UserRepository
import com.example.roomdatabaseexample.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//the job of the view model is to provide data to the ui and survive configuration changes.
// it acts as a communication channel between the repository and ui


//AndroidViewModel is different from normal viewmodel because it contains application reference
class UserViewModel(application: Application) : AndroidViewModel(application) {

     val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {

            repository.addUser(user)
        }

    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}