package kost.romi.repocommittimeline.data

import kost.romi.repocommittimeline.api.GitHubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubServiceRepository @Inject constructor(val service: GitHubService) {
//    suspend fun getSearchResult(
//        Authorization: String,
//        UserAgent: String,
//        query: String
//    ): SearchGHUserResponse {
//        return service.searchGHUser(Authorization, UserAgent, query)
//    }

    suspend fun getRateLimit() {
        return service.getRateLimit()
    }
}