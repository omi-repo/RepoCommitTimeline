package kost.romi.repocommittimeline

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    private val TAG = "appDebugViewModel"

}