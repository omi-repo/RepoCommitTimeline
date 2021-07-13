package kost.romi.repocommittimeline

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@HiltViewModel
class MainFragmentViewModel @Inject internal constructor(
) : ViewModel() {

    var searchThis = ""

//    private suspend fun getUser() {
////        val result = repository.searchUser(
////            token = BuildConfig.Token
////                    query =
////        )
//    }

}