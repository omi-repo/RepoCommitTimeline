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

    private val TAG = "addDEBUG"

    private val token = BuildConfig.Token
    private val userAgent = "kost.romi.repocommittimeline"

    private var _userNameEditText = MutableLiveData<String>("")
    val userNameEditText: LiveData<String>
        get() = _userNameEditText

    fun onUserNameChange(userName: String) {
        _userNameEditText.value = userName
    }

    private var userName = "${userNameEditText}+repos:>10+followers:>100"


    var response: MutableLiveData<SearchGHUserResponse>? = null

    fun getSearchResult() {

        Log.i(TAG, "Test")

        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.searchUser(
                BuildConfig.Token,
                userAgent,
                userName
            )

            Log.i(TAG, response.headers().toString())
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