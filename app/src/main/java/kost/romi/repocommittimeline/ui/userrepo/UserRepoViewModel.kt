package kost.romi.repocommittimeline.ui.userrepo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.BuildConfig
import kost.romi.repocommittimeline.SearchResponse
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepoViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    private val TAG = "appDebugViewModel"

    private val token = BuildConfig.Token
    private val userAgent = "kost.romi.repocommittimeline"
    private val sortBy = "updated"

    var userRepoUrl = ""

    fun getSearchResult() {
        var userRepoPath = userRepoUrl.replace("https://api.github.com/", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getUserRepo(
                token,
                userAgent,
                userRepoPath,
                sortBy
            )
            delay(1000)
            if (response.isSuccessful) {
                Log.i(TAG, response.headers().toString())
//                _searchResponse.postValue(GetRepoResponse.SUCCESS)
//                listUsersResponse = response.body()
//                Log.i(TAG, "Total count : ${listUsersResponse?.total_count.toString()}")
//                Log.i(TAG, "Item login : ${listUsersResponse?.items?.get(1)?.login.toString()}")
//                Log.i(TAG, "Item login : ${listUsersResponse?.items?.get(1)?.type.toString()}")
            } else {
//                _searchResponse.postValue(GetRepoResponse.FAIL)
            }
        }
    }

}

enum class GetRepoResponse {
    NONE, SUCCESS, FAIL
}