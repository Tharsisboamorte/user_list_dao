package com.example.userlistdao.feature.view.list_presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.userlistdao.database.entities.User
import com.example.userlistdao.feature.SortType
import com.example.userlistdao.feature.view.add_presentation.AddContactDialog
import com.example.userlistdao.feature.viewmodel.bloc.UserEvent
import com.example.userlistdao.feature.viewmodel.bloc.UserState
import com.example.userlistdao.ui.composables.ListItem
import com.example.userlistdao.ui.composables.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ListFragment(
    state: UserState,
    onEvent: (UserEvent) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = { Text(
            text = "List",
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
            color = MaterialTheme.colorScheme.onBackground
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(UserEvent.ShowDialog)
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { _ ->
        if(state.isAddingContact) {
            AddContactDialog(state = state, onEvent = onEvent)
        }
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
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

@Composable
@Preview
fun ListFragmentPreview() {
    val listOfUsers = mutableListOf<User>()
    listOfUsers.add(
        element = User(
            id = 1,
            firstName = "Tharsis",
            lastName = "Lamin",
            age = 24
        )
    )
}