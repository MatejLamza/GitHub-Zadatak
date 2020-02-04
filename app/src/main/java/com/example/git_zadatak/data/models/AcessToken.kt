package com.example.git_zadatak.data.models

import com.google.gson.annotations.SerializedName

data class AcessToken(
    @SerializedName("access_token")
    val acessToken: String,

    @SerializedName("token_type")
    val tokenType:String
)