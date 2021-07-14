package kost.romi.repocommittimeline.api

import kost.romi.repocommittimeline.data.GetUserRepoCommitsResponse
import kost.romi.repocommittimeline.data.GetUserReposResponse
import kost.romi.repocommittimeline.data.SearchGHUserResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface GitHubService {

    /*
    * Find repositories via various criteria.
    * This method returns up to 100 results per page.
    */
    @GET("search/users")
    suspend fun searchGHUser(
        @Header("Authorization") Authorization: String,
        @Header("User-Agent") UserAgent: String,
        @Query("q", encoded = true) q: String
    ): Response<SearchGHUserResponse>

    @GET("{user_id}")
    suspend fun getUserRepos(
        @Header("Authorization") Authorization: String,
        @Header("User-Agent") UserAgent: String,
        @Path("user_id", encoded = true) userId: String,
        @Query("sort", encoded = true) sort: String
    ): List<GetUserReposResponse>

    @GET("{user_repo_commits}")
    suspend fun getUserRepoCommits(
        @Header("Authorization") Authorization: String,
        @Header("User-Agent") UserAgent: String,
        @Path("user_repo_commits", encoded = true) userRepoCommits: String
    ): List<GetUserRepoCommitsResponse>

    @GET("rate_limit")
    suspend fun getRateLimit()

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        val client = OkHttpClient.Builder()
            .build()

        fun create(): GitHubService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
        }
    }

}