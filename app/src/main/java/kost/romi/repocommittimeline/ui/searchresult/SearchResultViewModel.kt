package kost.romi.repocommittimeline

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import kost.romi.repocommittimeline.data.Items
import kost.romi.repocommittimeline.data.SearchUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    private val TAG = "appDebugViewModel"

    private val token = BuildConfig.Token
    private val userAgent = "kost.romi.repocommittimeline"
    var page = 1

    private var _searchResponse = MutableLiveData(SearchResponse.NONE)
    val searchResponse: LiveData<SearchResponse> get() = _searchResponse

    //    var listUsersResponse: MutableList<Items>? = null
    var listUsersResponse = mutableListOf<Items>()

    private var _headerLink = MutableLiveData<String>("")
    val headerLink: LiveData<String> get() = _headerLink

    fun getSearchResult(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.searchUser(
                token,
                userAgent,
                userName,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
                //                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _searchResponse.postValue(SearchResponse.SUCCESS)
                response.body()?.items?.let { listUsersResponse?.addAll(it) }

                // handle header for different page request.
                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _searchResponse.postValue(SearchResponse.FAIL)
            }
        }
    }

    fun getNextSearchResult(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = gitHubServiceRepository.searchUser(
                token,
                userAgent,
                userName,
                page
            )
            delay(1000)
            if (response.isSuccessful) {
                //                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                for (map in response.headers().toMultimap()) {
                    Log.i(TAG, "keys: ${map.key} \t\t values: ${map.value}")
                }

                _searchResponse.postValue(SearchResponse.SUCCESS)
//                listUsersResponse = response.body()
//                response.body()?.items?.let { listUsersResponse?.addAll(it) }
                listUsersResponse.addAll(response.body()!!.items)
                Log.i(TAG, "getNextSearchResult:SIZE:  ${listUsersResponse.size}")

                // handle header for different page request.
                _headerLink.postValue(response.headers().toMultimap().get("link").toString())
            } else {
                Log.i(TAG, "response.headers().toString(): ${response.headers().toString()}")
                Log.i(TAG, "response.message(): ${response.message()}")
                _searchResponse.postValue(SearchResponse.FAIL)
                page--
            }
        }
    }

}

enum class SearchResponse {
    NONE, SUCCESS, FAIL
}