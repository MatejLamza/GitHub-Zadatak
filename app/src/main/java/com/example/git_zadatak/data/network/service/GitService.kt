package com.example.git_zadatak.data.network.service

import com.example.git_zadatak.data.models.GitResponseModel
import com.example.git_zadatak.data.models.GitUser
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitService {

    @GET("/search/repositories")
    fun getRepositories(@Query("q")query:String): Deferred<GitResponseModel>

    @GET("/users/{user}")
    fun getOwnerOfRepository(@Path("user")user:String):Deferred<GitUser>
}