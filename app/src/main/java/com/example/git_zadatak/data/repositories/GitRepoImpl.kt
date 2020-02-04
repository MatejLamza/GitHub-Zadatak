package com.example.git_zadatak.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.git_zadatak.data.network.GitDataSource
import com.example.git_zadatak.data.database.GitDAO
import com.example.git_zadatak.data.models.GitResponseModel
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.models.UserProfile
import com.example.git_zadatak.data.network.OnlineUserLogin
import kotlinx.coroutines.*
import javax.inject.Inject

class GitRepoImpl
@Inject constructor(val gitDataSource: GitDataSource,
                    val gitDao:GitDAO,
                    val onlineUserLogin: OnlineUserLogin): GitRepo {

    override suspend fun cacheUserProfile(userProfile: UserProfile) {
        GlobalScope.launch(Dispatchers.IO) {
            gitDao.upsertUserProfile(userProfile)
        }
    }

    override suspend fun fetchUserProfileFromCache(): UserProfile {
        return withContext(Dispatchers.IO){
            return@withContext gitDao.getUserProfil()
        }
    }

    override suspend fun signInWithGoogle(tokenId: String): LiveData<Boolean> {
        return withContext(Dispatchers.IO){
            return@withContext onlineUserLogin.signInWithGoogle(tokenId)
        }
    }

    override suspend fun fetchRepoOwnerDetailsFromCache(): GitUser {
        return withContext(Dispatchers.IO){
            return@withContext gitDao.getRepoOwner()
        }
    }

    override suspend fun cacheRepoOwner(gitUser: GitUser) {
        GlobalScope.launch(Dispatchers.IO){
            gitDao.upsertRepoOwner(gitUser)
        }
    }

    override suspend fun fetchRepoOwnerDetails(user: String): LiveData<GitUser> {
       return withContext(Dispatchers.IO){
           gitDataSource.fetchOwnerData(user)
           return@withContext gitDataSource.fetchedOwnerData
       }
    }

    override suspend fun fetchRepositories(query: String): LiveData<GitResponseModel> {
        return withContext(Dispatchers.IO){
            gitDataSource.fetchRepositories(query)
            return@withContext gitDataSource.fetchedRepositories
        }
    }
}