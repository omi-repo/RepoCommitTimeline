package kost.romi.repocommittimeline.data

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.*

data class UserRepoResponse(
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("node_id")
    val node_id: String,
    @field:SerializedName("full_name")
    val full_name: String,
    @field:SerializedName("private")
    val private: Boolean,
    @field:SerializedName("owner")
    val owner: Owner,
    @field:SerializedName("html_url")
    val html_url: URL,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("fork")
    val fork: Boolean,
    @field:SerializedName("url")
    val url: URL,
    @field:SerializedName("forks_url")
    val forks_url: URL,
    @field:SerializedName("keys_url")
    val keys_url: URL,
    @field:SerializedName("collaborators_url")
    val collaborators_url: URL,
    @field:SerializedName("teams_url")
    val teams_url: URL,
    @field:SerializedName("hooks_url")
    val hooks_url: URL,
    @field:SerializedName("issue_events_url")
    val issue_events_url: URL,
    @field:SerializedName("events_url")
    val events_url: URL,
    @field:SerializedName("assignees_url")
    val assignees_url: URL,
    @field:SerializedName("branches_url")
    val branches_url: URL,
    @field:SerializedName("tags_url")
    val tags_url: URL,
    @field:SerializedName("blobs_url")
    val blobs_url: URL,
    @field:SerializedName("git_tags_url")
    val git_tags_url: URL,
    @field:SerializedName("trees_url")
    val trees_url: URL,
    @field:SerializedName("languages_url")
    val languages_url: URL,
    @field:SerializedName("stargazers_url")
    val stargazers_url: URL,
    @field:SerializedName("contributors_url")
    val contributors_url: URL,
    @field:SerializedName("subscribers_url")
    val subscribers_url: URL,
    @field:SerializedName("subscription_url")
    val subscription_url: URL,
    @field:SerializedName("commits_url")
    val commits_url: URL,
    @field:SerializedName("git_commits_url")
    val git_commits_url: URL,
    @field:SerializedName("comments_url")
    val comments_url: URL,
    @field:SerializedName("issue_comment_url")
    val issue_comment_url: URL,
    @field:SerializedName("contents_url")
    val contents_url: URL,
    @field:SerializedName("merges_url")
    val merges_url: URL,
    @field:SerializedName("archive_url")
    val archive_url: URL,
    @field:SerializedName("downloads_url")
    val downloads_url: URL,
    @field:SerializedName("issues_url")
    val issues_url: URL,
    @field:SerializedName("pulls_url")
    val pulls_url: URL,
    @field:SerializedName("milestones_url")
    val milestones_url: URL,
    @field:SerializedName("notifications_url")
    val notifications_url: URL,
    @field:SerializedName("labels_url")
    val labels_url: URL,
    @field:SerializedName("releases_url")
    val releases_url: URL,
    @field:SerializedName("deployments_url")
    val deployments_url: URL,
    @field:SerializedName("created_at")
    val created_at: Date,
    @field:SerializedName("updated_at")
    val updated_at: Date,
    @field:SerializedName("pushed_at")
    val pushed_at: Date,
    @field:SerializedName("git_url")
    val git_url: URL,
    @field:SerializedName("clone_url")
    val clone_url: URL,
    @field:SerializedName("svn_url")
    val svn_url: URL,
    @field:SerializedName("homepage")
    val homepage: URL,
    @field:SerializedName("size")
    val size: Int,
    @field:SerializedName("stargazers_count")
    val stargazers_count: Int,
    @field:SerializedName("watchers_count")
    val watchers_count: Int,
    @field:SerializedName("language")
    val language: Int
)

data class Owner(
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("inode_id")
    val node_id: String,
    @field:SerializedName("avatar_url")
    val avatar_url: URL,
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
    val type: URL,
    @field:SerializedName("site_admin")
    val language: String,
    @field:SerializedName("has_issues")
    val has_issues: Boolean,
    @field:SerializedName("has_projects")
    val has_projects: Boolean,
    @field:SerializedName("has_downloads")
    val has_downloads: Boolean,
    @field:SerializedName("has_wiki")
    val has_wiki: Boolean,
    @field:SerializedName("has_pages")
    val has_pages: Boolean,
    @field:SerializedName("forks_count")
    val forks_count: Int,
    @field:SerializedName("mirror_url")
    val mirror_url: URL,
    @field:SerializedName("archived")
    val archived: Boolean,
    @field:SerializedName("disabled")
    val disabled: Boolean,
    @field:SerializedName("open_issues_count")
    val open_issues_count: Int,
    @field:SerializedName("license")
    val license: License,
    @field:SerializedName("forks")
    val forks: Int,
    @field:SerializedName("open_issues")
    val open_issues: Int,
    @field:SerializedName("watchers")
    val watchers: Int,
    @field:SerializedName("default_branch")
    val default_branch: String
)

data class License(
    @field:SerializedName("key")
    val key: String,
    @field:SerializedName("name")
    val keyname: String,
    @field:SerializedName("spdx_id")
    val spdx_id: String,
    @field:SerializedName("url")
    val url: URL,
    @field:SerializedName("node_id")
    val node_id: String
)