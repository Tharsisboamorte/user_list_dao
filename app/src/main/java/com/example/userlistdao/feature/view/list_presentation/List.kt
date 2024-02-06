package com.example.userlistdao.feature.view.list_presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.userlistdao.feature.SortType
import com.example.userlistdao.feature.view.add_presentation.AddContactDialog
import com.example.userlistdao.feature.viewmodel.bloc.UserEvent
import com.example.userlistdao.feature.viewmodel.bloc.UserState
import com.example.userlistdao.ui.composables.ListItem
import com.example.userlistdao.ui.composables.TopBar
import com.example.userlistdao.ui.theme.Purple80
import com.example.userlistdao.ui.theme.UserListDaoTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ListFragment(
    state: UserState,
    onEvent: (UserEvent) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = { TopBar(title = "List") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(UserEvent.ShowDialog)
                },
                containerColor = Purple80,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        if (state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }
        Box(
            Modifier
                .height(750.dp)
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding),
                verticalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                onEvent(UserEvent.SortUsers(SortType.ID))
                items(state.users) { user ->
                    ListItem(
                        id = user.id,
                        firstName = user.firstName,
                        lastName = user.lastName,
                        age = user.age,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ListFragmentPreview() {
    UserListDaoTheme {
        Surface {
            ListFragment(state = UserState(), onEvent = {})
        }
    }

}