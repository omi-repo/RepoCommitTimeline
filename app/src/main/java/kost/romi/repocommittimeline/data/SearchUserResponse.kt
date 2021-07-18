package kost.romi.repocommittimeline.data

import com.google.gson.annotations.SerializedName
import java.net.URI
import java.net.URL


data class SearchUserResponse(
    @field:SerializedName("total_count")
    val total_count: Int,
    @field:SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @field:SerializedName("items")
    val items: List<Items>
)

data class Items(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("avatar_url")
    val avatar_url: URL,
    @field:SerializedName("gravatar_id")
    val gravatar_id: String,
    @field:SerializedName("url")
    val url: URL,
    @field:SerializedName("html_url")
    val html_url: URL,
    @field:SerializedName("followers_url")
    val followers_url: URL,
    @field:SerializedName("following_url")
    val following_url: URL,
    @field:SerializedName("gists_url")
    val gists_url: URL,
    @field:SerializedName("starred_url")
    val starred_url: URL,
    @field:SerializedName("subscriptions_url")
    val subscriptions_url: URL,
    @field:SerializedName("organizations_url")
    val organizations_url: URL,
    @field:SerializedName("repos_url")
    val repos_url: URL,
    @field:SerializedName("events_url")
    val events_url: URL,
    @field:SerializedName("received_events_url")
    val received_events_url: URL,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("site_admin")
    val site_admin: Boolean,
    @field:SerializedName("score")
    val score: Float
)