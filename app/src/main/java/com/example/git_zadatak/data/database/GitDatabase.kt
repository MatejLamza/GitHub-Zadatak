package com.example.git_zadatak.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.models.UserProfile

@Database(entities = arrayOf(GitUser::class,UserProfile::class),version = 1)
abstract class GitDatabase : RoomDatabase() {
    abstract fun getGitDAO(): GitDAO
}