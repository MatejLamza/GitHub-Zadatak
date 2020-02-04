package com.example.git_zadatak.viewmodels.modules

import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.viewmodels.factories.LoginVMFactory
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun provideLoginVMFactory(gitRepo: GitRepo,googleSignInClient: GoogleSignInClient):LoginVMFactory{
        return LoginVMFactory(gitRepo,googleSignInClient)
    }
}