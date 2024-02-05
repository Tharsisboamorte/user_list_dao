package com.example.userlistdao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userlistdao.database.dao.UserDao
import com.example.userlistdao.database.entities.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract val dao: UserDao

}