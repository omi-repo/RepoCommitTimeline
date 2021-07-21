package kost.romi.repocommittimeline.ui.repocommit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.BuildConfig
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import kost.romi.repocommittimeline.data.RepoCommitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoCommitViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    private val TAG = "appDebugViewModel"

    private val token = BuildConfig.Token
    private val userAgent = "kost.romi.repocommittimeline"
    var page = 1

    private var _headerLink = MutableLiveData<String>("")
    val headerLink: LiveData<String> get() = _headerLink

    var repoCommitUrl = ""
    var userName = ""

    private val _getRepoCommitResponse = MutableLiveData(GetRepoCommitResponse.NONE)
    val getRepoCommitResponse: LiveData<GetRepoCommitResponse> get() = _getRepoCommitResponse

    var repoCommitResponse: List<RepoCommitResponse>? = null

    fun getRepoCommit() {
        var userRepoPath =
            repoCommitUrl.replace("https://api.github.com/", "").replace("{/sha}", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getRepoCommit(
                token,
                userAgent,
                userRepoPath,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
                //                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _getRepoCommitResponse.postValue(GetRepoCommitResponse.SUCCESS)
                repoCommitResponse = response.body()

                // handle header for different page request.
                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _getRepoCommitResponse.postValue(GetRepoCommitResponse.FAIL)
            }
        }
    }

    fun getNextRepoCommit() {
        var userRepoPath =
            repoCommitUrl.replace("https://api.github.com/", "").replace("{/sha}", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getRepoCommit(
                token,
                userAgent,
                userRepoPath,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
                //                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _getRepoCommitResponse.postValue(GetRepoCommitResponse.SUCCESS)
                repoCommitResponse = response.body()

                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _getRepoCommitResponse.postValue(GetRepoCommitResponse.FAIL)
                page--
            }
        }
    }

    fun getPervRepoCommit() {
        var userRepoPath =
            repoCommitUrl.replace("https://api.github.com/", "").replace("{/sha}", "")
        Log.i(TAG, "getSearchResult: ${userRepoPath}")
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.getRepoCommit(
                token,
                userAgent,
                userRepoPath,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
                //                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _getRepoCommitResponse.postValue(GetRepoCommitResponse.SUCCESS)
                repoCommitResponse = response.body()

                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _getRepoCommitResponse.postValue(GetRepoCommitResponse.FAIL)
                page++
            }
        }
    }

}

enum class GetRepoCommitResponse {
    NONE, SUCCESS, FAIL
}