package com.example.androidadvancedday12.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidadvancedday12.model.User
import com.example.androidadvancedday12.model.UserList
import com.example.androidadvancedday12.model.UserList.userList

class UserViewModel : ViewModel() {
    private val _data = MutableLiveData<List<User>>(emptyList())
    val data: LiveData<List<User>> get() = _data

    fun searchUser(query: String?) {
        println(query)
        if (query?.isNotEmpty() == true) {
            _data.value = _data.value?.filter {
                it.username.contains(query) || it.classname.contains(query)
            }
        } else {
            _data.value = userList
        }
    }

    val filteredUser: MutableList<User> = mutableListOf()
    val oldFilteredUser: MutableList<User> = mutableListOf()

    init {
        oldFilteredUser.addAll(userList)
        _data.value = userList
    }

//    fun filterOperator() : Observable<User>{
//        return Observable.fromIterablle
//    }
}