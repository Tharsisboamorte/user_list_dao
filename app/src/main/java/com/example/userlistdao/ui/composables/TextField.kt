package com.example.userlistdao.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.userlistdao.feature.viewmodel.bloc.UserEvent
import com.example.userlistdao.feature.viewmodel.bloc.UserState
import com.example.userlistdao.ui.theme.UserListDaoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    hint: String,
    text: String,
    onEvent: (UserEvent) -> Unit,
) {

    TextField(
        value = text,
        onValueChange = { newText ->
            onEvent(UserEvent.SetFirstName(newText))
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(4.dp),
        placeholder = {
            Text(
                text = hint,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White
        )
    )
}

@Preview
@Composable
fun RoundedTextFieldPreview() {

    UserListDaoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}