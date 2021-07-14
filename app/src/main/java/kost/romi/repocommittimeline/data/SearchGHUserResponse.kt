package kost.romi.repocommittimeline.data

import com.google.gson.annotations.SerializedName


data class SearchGHUserResponse(
    @field:SerializedName("total_count")
    val total_count: Int,
    @field:SerializedName("incomplete_results")
    val incomplete_results: Boolean
)