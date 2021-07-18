package kost.romi.repocommittimeline.ui.userrepo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.BuildConfig
import kost.romi.repocommittimeline.SearchResponse
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import kost.romi.repocommittimeline.data.SearchUserResponse
import kost.romi.repocommittimeline.data.UserRepoResponse
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
    private val type =
        "public"  // all, public, private, forks, sources, member, internal (Default: all)
    private val sortBy = "updated"  // created, updated, pushed, full_name (Default: created)

    var userRepoUrl = ""

    private val _getUserRepoResponse = MutableLiveData(GetUserRepoResponse.NONE)
    val getUserRepoResponse: LiveData<GetUserRepoResponse> get() = _getUserRepoResponse

    var listUsersResponse: List<UserRepoResponse>? = null

    fun getSearchResult() {
        var userRepoPath = userRepoUrl.replace("https://api.github.com/", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getUserRepo(
                token,
                userAgent,
                userRepoPath,
                type,
                sortBy
            )
            delay(1000)
            if (response.isSuccessful) {
                Log.i(TAG, response.headers().toString())
                _getUserRepoResponse.postValue(GetUserRepoResponse.SUCCESS)
                listUsersResponse = response.body()
                Log.i(TAG, "owner : ${listUsersResponse?.get(0)?.owner?.login}")
                Log.i(TAG, "repo name : ${listUsersResponse?.get(0)?.full_name}")
            } else {
                _getUserRepoResponse.postValue(GetUserRepoResponse.FAIL)
            }
        }
    }

}

enum class GetUserRepoResponse {
    NONE, SUCCESS, FAIL
}