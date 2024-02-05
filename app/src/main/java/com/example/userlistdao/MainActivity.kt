package com.example.userlistdao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.userlistdao.database.UserDatabase
import com.example.userlistdao.feature.view.list_presentation.ListFragment
import com.example.userlistdao.feature.viewmodel.UserViewModel
import com.example.userlistdao.ui.theme.UserListDaoTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    private val viewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserListDaoTheme {
                val state by viewModel.state.collectAsState()
                ListFragment(
                    state = state,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}