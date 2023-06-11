package com.codingwithme.notesapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithme.notesapp.entities.Notes
import com.codingwithme.notesapp.entities.Users

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: Users)

    @Query("SELECT * FROM users WHERE email=:email")
    suspend fun getUser(email: String) : Users
}