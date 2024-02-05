package com.example.userlistdao.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.userlistdao.feature.view.list_presentation.ListFragment
import com.example.userlistdao.feature.viewmodel.bloc.UserEvent
import com.example.userlistdao.feature.viewmodel.bloc.UserState

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    state: UserState,
    onEvent: (UserEvent) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ListFrag.route
    ) {
        composable(
            route = Screens.ListFrag.route
        ){
            ListFragment(
                state = state,
                onEvent = onEvent,
            )
        }
    }
}