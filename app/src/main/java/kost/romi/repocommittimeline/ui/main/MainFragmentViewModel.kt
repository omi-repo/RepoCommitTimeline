package kost.romi.repocommittimeline.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kost.romi.repocommittimeline.data.GitHubServiceRepository
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val gitHubServiceRepository: GitHubServiceRepository) :
    ViewModel() {

    private val TAG = "appDebugViewModel"

    private var _userNameEditText = MutableLiveData("")
    val userNameEditText: LiveData<String> get() = _userNameEditText

    fun onUserNameChange(userName: String) {
        _userNameEditText.value = userName
    }

}