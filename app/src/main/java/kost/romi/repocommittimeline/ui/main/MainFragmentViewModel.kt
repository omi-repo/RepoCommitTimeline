package kost.romi.repocommittimeline.ui.main

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.BuildConfig
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import kost.romi.repocommittimeline.data.SearchUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    private val TAG = "appDebugViewModel"

    private val token = BuildConfig.Token
    private val userAgent = "kost.romi.repocommittimeline"

    private var _userNameEditText = MutableLiveData<String>("")
    val userNameEditText: LiveData<String> get() = _userNameEditText

    fun onUserNameChange(userName: String) {
        _userNameEditText.value = userName
    }

    private var userName = "${userNameEditText}+repos:>10+followers:>100"


    var response: MutableLiveData<SearchUserResponse>? = null

    fun getSearchResult() {

        Log.i(TAG, "Test")

        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.searchUser(
                token,
                userAgent,
                userName
            )

            if (response.isSuccessful) {
                Log.i(TAG, response.headers().toString())
            } else {

            }
        }


        /*
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
         */
    }

}