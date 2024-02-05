package com.example.userlistdao.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.userlistdao.ui.theme.UserListDaoTheme

@Composable
fun ListItem(
    id: Int,
    firstName: String,
    lastName: String,
    age: Int,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(35.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 50.dp
            ),
            text = id.toString(),
            style = TextStyle(
                color = Color.DarkGray,
            ),
            fontSize = 25.sp
        )
        Text(
            modifier = Modifier.padding(
                end = 20.dp
            ),
            text = "$firstName $lastName",
            style = TextStyle(
                color = Color.Gray
            ),
            fontSize = 18.sp
        )
        Text(
            text = age.toString(),
            style = TextStyle(
                color = Color.Gray
            ),
            fontSize = 18.sp,
            )
    }
}

@Preview
@Composable
fun ListItemPreview() {
    UserListDaoTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ListItem(
                id = 1,
                firstName = "Guilherme",
                lastName = "Santiago",
                age = 24
            )
        }
    }
}