package com.codingwithme.notesapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Users")
class Users: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @ColumnInfo(name = "email")
    var email:String? = null

    @ColumnInfo(name = "name")
    var name:String? = null

    @ColumnInfo(name = "password")
    var password:String? = null
}