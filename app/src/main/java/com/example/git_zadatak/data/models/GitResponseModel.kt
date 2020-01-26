package com.example.git_zadatak.data.models


import com.google.gson.annotations.SerializedName

data class GitResponseModel(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)