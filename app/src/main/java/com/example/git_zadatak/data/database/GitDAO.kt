package com.example.git_zadatak.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.git_zadatak.data.models.CURRENT_REPO_OWNER_ID
import com.example.git_zadatak.data.models.GitUser

@Dao
interface GitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertRepoOwner(gitUser: GitUser)

    @Query("SELECT * FROM repo_owner WHERE idRepoOwner = $CURRENT_REPO_OWNER_ID")
    fun getRepoOwner():GitUser


}