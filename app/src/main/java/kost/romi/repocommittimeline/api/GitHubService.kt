package kost.romi.repocommittimeline.api

import kost.romi.repocommittimeline.data.RepoCommitResponse
import kost.romi.repocommittimeline.data.SearchUserResponse
import kost.romi.repocommittimeline.data.UserRepoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    ): Response<SearchUserResponse>

    @GET("{user_repo_url}")
    suspend fun getUserRepos(
        @Header("Authorization") Authorization: String,
        @Header("User-Agent") UserAgent: String,
        @Path("user_repo_url", encoded = true) user_repo_url: String,
        @Query("type", encoded = true) type: String,
        @Query("sort", encoded = true) sort: String
    ): Response<List<UserRepoResponse>>

    @GET("{user_repo_commits}")
    suspend fun getRepoCommits(
        @Header("Authorization") Authorization: String,
        @Header("User-Agent") UserAgent: String,
        @Path("user_repo_commits", encoded = true) userRepoCommits: String
    ): Response<List<RepoCommitResponse>>

    @GET("rate_limit")
    suspend fun getRateLimit()

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): GitHubService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
        }
    }


}