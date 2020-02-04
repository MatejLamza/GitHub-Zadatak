package com.example.git_zadatak.viewmodels.modules

import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.viewmodels.factories.UserProfileVMFactory
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.Module
import dagger.Provides

@Module
class UserProfileModule {
    @Provides
    fun provideUserProfileVMFactory(gitRepo: GitRepo,googleSignInClient: GoogleSignInClient):UserProfileVMFactory{
        return UserProfileVMFactory(gitRepo,googleSignInClient)
    }
}