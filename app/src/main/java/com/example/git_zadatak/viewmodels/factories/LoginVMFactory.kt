package com.example.git_zadatak.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.viewmodels.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import java.lang.IllegalArgumentException
import javax.inject.Inject

class LoginVMFactory
@Inject constructor(val gitRepo: GitRepo,val googleSignInClient: GoogleSignInClient):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) return LoginViewModel(gitRepo,googleSignInClient) as T
        else{
            throw IllegalArgumentException("Unkown class Login ViewModel")
        }
    }
}