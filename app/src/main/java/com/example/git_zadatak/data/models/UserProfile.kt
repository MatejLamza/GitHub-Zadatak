package com.example.git_zadatak.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity(tableName = "profiles")
data class UserProfile(
    var email:String? = "nullMail",
    var name:String? = "nullName",
    var photoURL:String? = "nullUrl"
){
    @PrimaryKey(autoGenerate = false)
    var idUserProfile:Int = CURRENT_USER_ID
}