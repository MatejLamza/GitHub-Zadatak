package com.example.git_zadatak.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.git_zadatak.utils.MyConsts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class OnlineUserLoginImpl
@Inject constructor(val firebaseAuth:FirebaseAuth):OnlineUserLogin{

    override suspend fun signInWithGoogle(tokenId: String): MutableLiveData<Boolean> {
        val liveLoginSuccess: MutableLiveData<Boolean> = MutableLiveData()
        val credential = GoogleAuthProvider.getCredential(tokenId, null)

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(MyConsts.LOG_TAG, "signInWithCredential:success")
                    liveLoginSuccess.value = true
                } else {
                    Log.w(MyConsts.LOG_TAG, "signInWithCredential:failure", task.exception)
                    liveLoginSuccess.value = false
                }
            }
        return liveLoginSuccess
    }


}