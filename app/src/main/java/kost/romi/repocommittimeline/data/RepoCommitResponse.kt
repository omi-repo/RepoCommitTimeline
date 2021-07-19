package kost.romi.repocommittimeline.data

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.*

data class RepoCommitResponse(
    @field:SerializedName("sha")
    val sha: String,
    @field:SerializedName("node_id")
    val node_id: String,
    @field:SerializedName("commit")
    val commit: Commit,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("html_url")
    val html_url: String,
    @field:SerializedName("comments_url")
    val comments_url: String,
    @field:SerializedName("author")
    val author: AuthorLong,
    @field:SerializedName("committer")
    val committer: committerLong,
)

data class committerLong(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("avatar_url")
    val avatar_url: String,
    @field:SerializedName("gravatar_id")
    val gravatar_id: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("html_url")
    val html_url: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("site_admin")
    val site_admin: Boolean
)

data class AuthorLong(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("node_id")
    val node_id: String,
    @field:SerializedName("avatar_url")
    val avatar_url: String,
    @field:SerializedName("gravatar_id")
    val gravatar_id: String,
    @field:SerializedName("html_url")
    val html_url: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("site_admin")
    val site_admin: String
)

data class Commit(
    @field:SerializedName("author")
    val author: Author,
    @field:SerializedName("committer")
    val committer: Committer,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("tree")
    val tree: Tree,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("comment_count")
    val comment_count: Int,
    @field:SerializedName("verification")
    val verification: Verification
)

data class Author(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("date")
    val date: Date,
)

data class Committer(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("date")
    val date: Date
)

data class Tree(
    @field:SerializedName("sha")
    val sha: String,
    @field:SerializedName("url")
    val url: String
)

data class Verification(
    @field:SerializedName("verified")
    val verified: Boolean,
    @field:SerializedName("reason")
    val reason: String,
    @field:SerializedName("signature")
    val signature: String,
    @field:SerializedName("payload")
    val payload: String
)