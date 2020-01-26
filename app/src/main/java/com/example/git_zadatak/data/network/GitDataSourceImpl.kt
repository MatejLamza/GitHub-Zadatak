package com.example.git_zadatak.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.git_zadatak.data.models.GitResponseModel
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.network.service.GitService
import java.lang.Exception
import javax.inject.Inject

class GitDataSourceImpl
@Inject constructor(val gitService: GitService) :
    GitDataSource {

    private var _fetchedOwnerData = MutableLiveData<GitUser>()
    private var _fetchedRepositories = MutableLiveData<GitResponseModel>()

    override val fetchedOwnerData: LiveData<GitUser>
        get() = _fetchedOwnerData

    override val fetchedRepositories: LiveData<GitResponseModel>
        get() = _fetchedRepositories

    override suspend fun fetchRepositories(query: String) {
        try {
            val fetchedRepositories = gitService.getRepositories(query).await()
            _fetchedRepositories.postValue(fetchedRepositories)
        } catch (exception:Exception){

        }
    }

    override suspend fun fetchOwnerData(user: String) {
        try {
            val ownerData = gitService.getOwnerOfRepository(user).await()
            _fetchedOwnerData.postValue(ownerData)
        } catch (exception:Exception){

        }
    }
}