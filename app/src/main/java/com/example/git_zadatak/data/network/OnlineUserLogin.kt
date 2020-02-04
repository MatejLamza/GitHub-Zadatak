package com.example.git_zadatak.data.network

import androidx.lifecycle.MutableLiveData

interface OnlineUserLogin {

    suspend fun signInWithGoogle(tokenId:String):MutableLiveData<Boolean>
}