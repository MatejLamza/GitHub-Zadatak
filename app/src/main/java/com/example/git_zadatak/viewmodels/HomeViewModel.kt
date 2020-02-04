package com.example.git_zadatak.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.git_zadatak.data.models.GitResponseModel
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.models.UserProfile
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.utils.MyConsts
import com.example.git_zadatak.view.ui.OwnerDetailsActivity
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel
@Inject constructor(val gitRepo: GitRepo) : ViewModel() {
    var liveRepoOwner = MutableLiveData<GitUser>()
    var liveRepos = MutableLiveData<GitResponseModel>()
    var spinner: MutableLiveData<Boolean> = MutableLiveData()


    fun getNewRepos(query: String) {
        viewModelScope.launch {
            try {
                spinner.value = true
                liveRepos.value = gitRepo.fetchRepositories(query).value
            } catch (ex: Exception) {
                Log.d(MyConsts.LOG_TAG, "Get New Repos VM:${ex.message}")
            } finally {
                spinner.value = false
            }
        }
    }

    fun cacheRepoOwner(gitUser: GitUser) {
        viewModelScope.launch {
            try {
                gitRepo.cacheRepoOwner(gitUser)
            } catch (ex: Exception) {
                Log.d(MyConsts.LOG_TAG, "Cache Repo Owner VM ${ex.message}")
            }
        }
    }

    fun getRepoOwnerDetails(user: String) {
        viewModelScope.launch {
            try {
                liveRepoOwner.value = gitRepo.fetchRepoOwnerDetails(user).value
            } catch (ex: Exception) {
                Log.d(MyConsts.LOG_TAG,"GetRepoOwner Details VM: ${ex.message}")
            }
        }
    }

    fun redirectUserToNewActivity(context: Context) {
        val intent = Intent(context, OwnerDetailsActivity::class.java)
        context.startActivity(intent)
    }
}