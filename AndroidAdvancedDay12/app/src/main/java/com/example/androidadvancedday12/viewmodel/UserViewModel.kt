package com.example.androidadvancedday12.viewmodel

import android.database.Observable
import androidx.lifecycle.ViewModel
import com.example.androidadvancedday12.model.User
import com.example.androidadvancedday12.model.UserList.userList

class UserViewModel: ViewModel() {
    fun searchUser(query: String) {

    }

    val filteredUser: MutableList<User> = mutableListOf()
    val oldFilteredUser: MutableList<User> = mutableListOf()

    init {
        oldFilteredUser.addAll(userList)
    }

//    fun filterOperator() : Observable<User>{
//        return Observable.fromIterablle
//    }
}