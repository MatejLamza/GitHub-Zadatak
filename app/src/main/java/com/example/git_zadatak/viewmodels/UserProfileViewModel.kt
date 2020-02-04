package com.example.git_zadatak.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.git_zadatak.data.models.UserProfile
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.utils.MyConsts
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class UserProfileViewModel
@Inject constructor(val gitRepo: GitRepo,val googleSignInClient: GoogleSignInClient):ViewModel() {

    var liveUserProfile = MutableLiveData<UserProfile>()

    fun googleSignOut(){
        googleSignInClient.signOut()
    }

    fun getCachedUserProfile(){
        viewModelScope.launch {
            try {
                liveUserProfile.value = gitRepo.fetchUserProfileFromCache()
            } catch (ex:Exception){
                Log.d(MyConsts.LOG_TAG,"Get Cached User Profile: ${ex.message}")
            }
        }
    }
}