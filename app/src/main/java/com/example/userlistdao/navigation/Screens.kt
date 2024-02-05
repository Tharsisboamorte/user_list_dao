package com.example.userlistdao.navigation


sealed class Screens(val route: String){
    object ListFrag: Screens(route = "List_Fragment")
    object AddFrag: Screens(route = "Add_Fragment")
}
