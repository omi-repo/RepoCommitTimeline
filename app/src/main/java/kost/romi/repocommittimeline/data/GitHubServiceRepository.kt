package kost.romi.repocommittimeline.data

import kost.romi.repocommittimeline.api.GitHubService
import retrofit2.Response
import javax.inject.Inject

class GitHubServiceRepository @Inject constructor(private val service: GitHubService) {

    suspend fun searchUser(
        token: String,
        userAgent: String,
        user: String
    ): Response<SearchGHUserResponse> {
        return service.searchGHUser(token, userAgent, user)
    }

}