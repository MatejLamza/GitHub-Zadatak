package com.example.git_zadatak.viewmodels.modules

import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.viewmodels.factories.HomeVMFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun provideHomeVMFactory(gitRepo: GitRepo):HomeVMFactory{
        return HomeVMFactory(gitRepo)
    }
}