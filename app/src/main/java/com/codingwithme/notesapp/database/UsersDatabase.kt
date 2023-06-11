package com.codingwithme.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingwithme.notesapp.dao.UserDao
import com.codingwithme.notesapp.entities.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    companion object {
        var usersDatabase: UsersDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): UsersDatabase {
            if (usersDatabase == null) {
                usersDatabase = Room.databaseBuilder(
                    context
                    , UsersDatabase::class.java
                    , "users.db"
                ).fallbackToDestructiveMigration().build()
            }
            return usersDatabase!!
        }
    }

    abstract fun userDao(): UserDao
}