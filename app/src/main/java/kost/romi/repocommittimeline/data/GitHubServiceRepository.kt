package kost.romi.repocommittimeline.data

import kost.romi.repocommittimeline.api.GitHubService
import retrofit2.Response
import javax.inject.Inject

class GitHubServiceRepository @Inject constructor(private val service: GitHubService) {

    suspend fun searchUser(
        token: String,
        userAgent: String,
        user: String
    ): Response<SearchUserResponse> {
        return service.searchGHUser(token, userAgent, user)
    }

    suspend fun getUserRepo(
        token: String,
        userAgent: String,
        userRepoUrl: String,
        type: String,
        sort: String
    ): Response<List<UserRepoResponse>> {
        return service.getUserRepos(token, userAgent, userRepoUrl, type, sort)
    }

}