package kost.romi.repocommittimeline.data

import com.google.gson.annotations.SerializedName

data class GetUserRepoCommitsResponse(
    @field:SerializedName("sha")
    var sha: String?,
    @field:SerializedName("node_id")
    var nodeId: String?,
    @field:SerializedName("url")
    var url: String?
)
