package com.example.git_zadatak.data.network

import androidx.lifecycle.LiveData
import com.example.git_zadatak.data.models.GitResponseModel
import com.example.git_zadatak.data.models.GitUser

interface GitDataSource {

    val fetchedRepositories:LiveData<GitResponseModel>
    val fetchedOwnerData:LiveData<GitUser>

    suspend fun fetchRepositories(query:String)
    suspend fun fetchOwnerData(user:String)
}