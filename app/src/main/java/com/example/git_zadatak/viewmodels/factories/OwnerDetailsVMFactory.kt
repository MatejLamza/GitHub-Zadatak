package com.example.git_zadatak.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.viewmodels.OwnerDetailsViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class OwnerDetailsVMFactory
@Inject constructor(val gitRepo: GitRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(OwnerDetailsViewModel::class.java)){
            return OwnerDetailsViewModel(gitRepo) as T
        } else{
            throw IllegalArgumentException("No such class as OwnerDetails VM!!")
        }
    }
}