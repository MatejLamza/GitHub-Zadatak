package com.example.git_zadatak.data.repositories

import androidx.lifecycle.LiveData
import com.example.git_zadatak.data.models.GitResponseModel
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.models.UserProfile

interface GitRepo {

    suspend fun fetchRepositories(query:String): LiveData<GitResponseModel>
    suspend fun fetchRepoOwnerDetails(user:String): LiveData<GitUser>

    //Online Login
    suspend fun signInWithGoogle(tokenId:String):LiveData<Boolean>

    //Offline Database
    suspend fun cacheRepoOwner(gitUser: GitUser)
    suspend fun cacheUserProfile(userProfile: UserProfile)

    suspend fun fetchRepoOwnerDetailsFromCache():GitUser
    suspend fun fetchUserProfileFromCache():UserProfile
}