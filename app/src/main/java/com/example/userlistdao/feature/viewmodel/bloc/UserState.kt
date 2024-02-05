package com.example.userlistdao.feature.viewmodel.bloc

import com.example.userlistdao.database.entities.User
import com.example.userlistdao.feature.SortType

data class UserState(
    val users: List<User> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val age: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)