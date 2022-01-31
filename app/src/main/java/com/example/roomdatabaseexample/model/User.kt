package com.example.roomdatabaseexample.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


//this user class represents entity(a table) in the database
@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)//meaning the id is a primary key
                                    // and room will generate it automatically
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
): Parcelable


