package com.example.userlistdao.feature.viewmodel.bloc

import com.example.userlistdao.database.entities.User
import com.example.userlistdao.feature.SortType

sealed interface UserEvent {
    object SaveUser: UserEvent
    data class SetFirstName(val firstName: String): UserEvent
    data class SetLastName(val lastName: String): UserEvent
    data class SetAge(val age: String): UserEvent
    object ShowDialog: UserEvent
    object HideDialog: UserEvent
    data class DeleteUser(val user: User): UserEvent
    data class SortUsers(val sortType: SortType): UserEvent
}