package com.example.git_zadatak.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.git_zadatak.data.models.GitUser
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.utils.MyConsts
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class OwnerDetailsViewModel
@Inject constructor(val gitRepo:GitRepo) :ViewModel() {

    var liveRepoOwner = MutableLiveData<GitUser>()

    fun getCachedRepoOwner(){
        viewModelScope.launch {
            try {
                liveRepoOwner.value = gitRepo.fetchRepoOwnerDetailsFromCache()
            } catch (ex:Exception){
                Log.d(MyConsts.LOG_TAG,"Get Cached Repo Owner VM:  ${ex.message}")
            }
        }
    }

}