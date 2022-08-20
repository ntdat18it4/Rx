package com.example.androidadvancedday12.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidadvancedday12.model.UserList
import io.reactivex.rxjava3.subjects.PublishSubject

class UserViewModel : ViewModel() {
    private val query = PublishSubject.create<String>()

    val users = query
        .distinctUntilChanged()
        .map {
            UserList.userList.filter { user -> user.username.contains(it, ignoreCase = true) ||user.classname.contains(it, ignoreCase = true) }
        }
        .distinctUntilChanged()

    fun onSearchQuery(newText: String) {
        query.onNext(newText)
    }
}