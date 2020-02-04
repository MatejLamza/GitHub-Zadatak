package com.example.git_zadatak.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.git_zadatak.data.models.CURRENT_REPO_OWNER_ID
import com.example.git_zadatak.data.models.CURRENT_USER_ID
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.models.UserProfile

@Dao
interface GitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertRepoOwner(gitUser: GitUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM repo_owner WHERE idRepoOwner = $CURRENT_REPO_OWNER_ID")
    fun getRepoOwner():GitUser

    @Query("SELECT * FROM profiles WHERE idUserProfile = $CURRENT_USER_ID")
    fun getUserProfil():UserProfile

}