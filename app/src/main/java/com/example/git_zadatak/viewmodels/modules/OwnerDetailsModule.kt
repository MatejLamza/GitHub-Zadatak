package com.example.git_zadatak.viewmodels.modules

import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.viewmodels.factories.OwnerDetailsVMFactory
import dagger.Module
import dagger.Provides

@Module
class OwnerDetailsModule {
    @Provides
    fun provideOwnerDetailsVMFactory(gitRepo: GitRepo):OwnerDetailsVMFactory{
        return OwnerDetailsVMFactory(gitRepo)
    }
}