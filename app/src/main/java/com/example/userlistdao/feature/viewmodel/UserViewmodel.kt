package com.example.userlistdao.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlistdao.database.dao.UserDao
import com.example.userlistdao.database.entities.User
import com.example.userlistdao.feature.SortType
import com.example.userlistdao.feature.viewmodel.bloc.UserEvent
import com.example.userlistdao.feature.viewmodel.bloc.UserState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModel(
    private val dao: UserDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _users = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.FIRST_NAME -> dao.getContactsOrderedByFirstName()
                SortType.LAST_NAME -> dao.getContactsOrderedByLastName()
                SortType.AGE -> dao.getContactsOrderedByAge()
                SortType.ID -> dao.getUsersById()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(UserState())
    val state = combine(_state, _sortType, _users) { state, sortType, users ->
        state.copy(
            users = users,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.DeleteUser -> {
                viewModelScope.launch {
                    dao.deleteUser(event.user)
                }
            }

            UserEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            UserEvent.SaveUser -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val age = state.value.age

                if (firstName.isBlank() || lastName.isBlank() || age.isBlank()) {
                    return
                }

                val user = User(
                    firstName = firstName,
                    lastName = lastName,
                    age = age.toInt(),
                )

                viewModelScope.launch {
                    dao.upsertUser(user)
                }

                _state.update {
                    it.copy(
                        isAddingContact = false,
                        firstName = "",
                        lastName = "",
                        age = "",
                    )
                }
            }

            is UserEvent.SetAge -> {
                _state.update {
                    it.copy(
                        age = event.age
                    )
                }
            }

            is UserEvent.SetFirstName -> {
                _state.update {
                    it.copy(
                        firstName = event.firstName
                    )
                }
            }

            is UserEvent.SetLastName -> {
                _state.update {
                    it.copy(
                        lastName = event.lastName
                    )
                }
            }

            UserEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }

            is UserEvent.SortUsers -> {
                _sortType.value = event.sortType
            }
        }
    }
}