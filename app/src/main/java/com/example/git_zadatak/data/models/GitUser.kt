package com.example.git_zadatak.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_REPO_OWNER_ID = 0

@Entity(tableName = "repo_owner")
data class GitUser(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    var bio: String? = "null",
    val blog: String,
    var company: String? = "null",
    @SerializedName("created_at")
    val createdAt: String,
    var email: String? = "null2",
    @SerializedName("events_url")
    val eventsUrl: String,
    val followers: Int,
    @SerializedName("followers_url")
    val followersUrl: String,
    val following: Int,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    var hireable: Boolean? = false,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    var location: String? = "null",
    val login: String,
    var name: String? = "null",
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("public_gists")
    val publicGists: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String
) {
    @PrimaryKey(autoGenerate = false)
    var idRepoOwner:Int = CURRENT_REPO_OWNER_ID
}