package kost.romi.repocommittimeline

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import kost.romi.repocommittimeline.data.SearchGHUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    val TAG = "addDEBUG"

    val user = "jake"
    val choosenUser = "JakeWharton"

    // https://api.github.com/search/users?q=jake
//    val searchUsers = "${user}+repos:>42+followers:>1000"  // q parameter
    val searchUsers = "${user}"  // q parameter

    // "repos_url": "https://api.github.com/users/JakeWharton/repos",
    // https://api.github.com/users/JakeWharton/repos?sort=pushed
    val searchUserRepos = "users/${choosenUser}/repos?sort=pushed"

    // "commits_url": "https://api.github.com/repos/JakeWharton/jakewharton.com/commits{/sha}",
    val getUserRepoCommits = "repos/JakeWharton/jakewharton.com/commits"

    var searchResult: String = ""

    var response: MutableLiveData<SearchGHUserResponse>? = null

    fun getSearchResult() {

        Log.i(TAG, "Test")

        viewModelScope.launch(Dispatchers.IO) {
//            repository.searchUser(BuildConfig.Token, "kost.romi.repocommittimeline", "jake")
//            repository.search(BuildConfig.Token, "kost.romi.repocommittimeline", user)
//            networkModule.provideGitHubService().searchGHUser(BuildConfig.Token, "kost.romi.repocommittimeline", user)
            gitHubServiceRepository.searchUser(BuildConfig.Token, "kost.romi.repocommittimeline", user)
        }


//        viewModelScope.launch {
//            val response = repository.getSearchResult(searchThis)
//            Timber.i("total_count: ${response.await().total_count}")
//        }
        /*
        // Interceptor
        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GitHubService::class.java)

        viewModelScope.launch(Dispatchers.IO) {

            Timber.i("viewModelScope.launch(Dispatchers.IO) {")

            // Search a user
            val response = service.searchGHUser(
                "token ${BuildConfig.Token}",
                "kost.romi.repocommittimeline",
                searchUsers
            )

            // Response
            if (response.isSuccessful) {
                Log.i("viewmodel", "RESPONSE: ${response.body()?.total_count}")
                Log.i("viewmodel", "RESPONSE: ${response.headers().get("link")}")  // get header
//                Timber.i("Total count: ${response.body()?.total_count}")
            }

//            val response = deferred.await()
//            try {
//                when (deferred.isCompleted) {
//                    true -> {
//                        val searchGHUserResponse = response.body()
//                        Timber.i("BODY: ${searchGHUserResponse.toString()}")
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e)
//            }

            // Get User repo
//            val str = service.getUserRepos(
//                "token ${BuildConfig.Token}",
//                "kost.romi.repocommittimeline",
//                "users/JakeWharton/repos",
//                "pushed"
//            )

            // Get User Repo Commits
//            val str = service.getUserRepoCommits(
//                "token ${BuildConfig.Token}",
//                "kost.romi.repocommittimeline",
//                "$getUserRepoCommits"
//            )

            // Get rate limit
//            val deferred = service.getRateLimit()

//            val response: GitHubServiceResponse = deferred.getCompleted()
//            Timber.i("RESPONSE: ${response.total_count}")
        }

        Timber.i("TEST")
         */
    }

}