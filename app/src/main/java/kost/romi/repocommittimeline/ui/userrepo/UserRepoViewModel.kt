package kost.romi.repocommittimeline.ui.userrepo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.BuildConfig
import kost.romi.repocommittimeline.data.GitHubServiceRepository
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
    var page = 1

    var userRepoUrl = ""

    private var _headerLink = MutableLiveData<String>("")
    val headerLink: LiveData<String> get() = _headerLink

    private val _getUserRepoResponse = MutableLiveData(GetUserRepoResponse.NONE)
    val getUserRepoResponse: LiveData<GetUserRepoResponse> get() = _getUserRepoResponse

    //    var listUsersResponse: List<UserRepoResponse>? = null
    var listUsersResponse = mutableListOf<UserRepoResponse>()

    fun getUserRepo() {
        var userRepoPath = userRepoUrl.replace("https://api.github.com/", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getUserRepo(
                token,
                userAgent,
                userRepoPath,
                type,
                sortBy,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
//                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _getUserRepoResponse.postValue(GetUserRepoResponse.SUCCESS)
                response.body()?.let { listUsersResponse.addAll(it) }

                // handle header for different page request.
                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _getUserRepoResponse.postValue(GetUserRepoResponse.FAIL)
            }
        }
    }

    fun getNextUserRepo() {
        var userRepoPath = userRepoUrl.replace("https://api.github.com/", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getUserRepo(
                token,
                userAgent,
                userRepoPath,
                type,
                sortBy,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
//                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _getUserRepoResponse.postValue(GetUserRepoResponse.SUCCESS)
                response.body()?.let { listUsersResponse.addAll(it) }

                // handle header for different page request.
                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _getUserRepoResponse.postValue(GetUserRepoResponse.FAIL)
                page--
            }
        }
    }

}

enum class GetUserRepoResponse {
    NONE, SUCCESS, FAIL
}