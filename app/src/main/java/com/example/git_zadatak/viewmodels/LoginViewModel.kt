package com.example.git_zadatak.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.git_zadatak.data.models.UserProfile
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.utils.MyConsts
import com.example.git_zadatak.utils.internal.ActivityNavigation
import com.example.git_zadatak.utils.internal.LiveMessageEvent
import com.example.git_zadatak.view.ui.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel
@Inject constructor(val gitRepo: GitRepo, val mGoogleSignInClient: GoogleSignInClient) : ViewModel() {

    val startActivityForResultEvent = LiveMessageEvent<ActivityNavigation>()
    var spinner: MutableLiveData<Boolean> = MutableLiveData()
    var loginSuccess: MediatorLiveData<Boolean> = MediatorLiveData()

    fun checkIfUserIsLoggedIn(context: Context) {
        val currentSingedAccount = GoogleSignIn.getLastSignedInAccount(context)
        if (currentSingedAccount != null){
            forwardToHomeScreen(context)
        }
    }

    fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResultEvent.sendEvent {
            startActivityForResult(signInIntent, MyConsts.RC_SIGN_IN)
        }
    }

    private fun cacheUserProfile(userProfile: UserProfile){
        viewModelScope.launch {
            try {
                gitRepo.cacheUserProfile(userProfile)
            } catch (ex:Exception){
                Log.d(MyConsts.LOG_TAG,"Cache User Profile: ${ex.message}")
            }
        }
    }

    fun onResultFromActivity(requestCode:Int?, data: Intent?){
        when (requestCode) {
            MyConsts.RC_SIGN_IN -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        val user = UserProfile(account.email!!,account.displayName!!,account.photoUrl.toString())
                        cacheUserProfile(user)
                        viewModelScope.launch {
                            try {
                                spinner.value = true
                                loginSuccess.addSource(gitRepo.signInWithGoogle(account.idToken!!)) {
                                    loginSuccess.value = it
                                }
                            } catch (exception:Exception){
                                Log.d(MyConsts.LOG_TAG,"Google Sing in:${exception.message}")
                            } finally {
                                spinner.value = false
                            }
                        }
                    }
                } catch (exc:ApiException){
                    Log.w(MyConsts.LOG_TAG, "Google sign in failed", exc)
                }
            }
        }
    }

    fun forwardToHomeScreen(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }
}