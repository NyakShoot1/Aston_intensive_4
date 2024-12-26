package ru.nyakshoot.aston_intensive_4.presentation.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nyakshoot.aston_intensive_4.data.User

class UserListViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        _users.value = List(30) {
            User(it, "Photo$it.jpg", "User$it", "LastName$it", "phone$it")
        }
    }

    fun updateUser(updatedUser: User) {
        val currentUsers = _users.value?.toMutableList() ?: mutableListOf()
        val index = currentUsers.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            currentUsers[index] = updatedUser
            _users.value = currentUsers
        }
    }
}
