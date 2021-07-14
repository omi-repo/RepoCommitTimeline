package kost.romi.repocommittimeline.data

import com.google.gson.annotations.SerializedName


data class GetUserReposResponse(
    @field:SerializedName("id")
    var userId: String?,
    @field:SerializedName("node_id")
    var nodeId: String?,
    @field:SerializedName("name")
    var repoName: String?,
    @SerializedName("full_name")
    var repoFullName: String?,
    @SerializedName("private")
    var private: Boolean?
)