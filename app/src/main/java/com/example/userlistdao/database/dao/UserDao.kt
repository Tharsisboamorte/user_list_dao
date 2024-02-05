package com.example.userlistdao.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.userlistdao.database.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUsersById(): Flow<List<User>>

    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName(): Flow<List<User>>

    @Query("SELECT * FROM user ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<User>>

    @Query("SELECT * FROM user ORDER BY age ASC")
    fun getContactsOrderedByAge(): Flow<List<User>>

    @Delete
    suspend fun deleteUser(user: User)

}