package kost.romi.repocommittimeline

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.databinding.FragmentMainBinding
import timber.log.Timber

/**
 * TODO: use <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>, to handle Oauth2. [CHECK]
 * TODO: add search result to DialogFragment.
 * @see <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>
 */

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val TAG = "appDebugFragment"

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "override fun onViewCreated(view: View, savedInstanceState: Bundle?)")

        binding.searchEditText.setText(viewModel.userNameEditText.value)
        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            viewModel.onUserNameChange(text.toString())
        }

        binding.searchButton.setOnClickListener {
            hideSoftKeyboard(requireView())
            if (binding.searchEditText.text.isEmpty()) {
                Snackbar
                    .make(
                        binding.mainContainer,
                        "Search bar can't be empty",
                        Snackbar.LENGTH_LONG
                    )
                    .setAnchorView(binding.bottomAppBar)
                    .show()
            } else {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchResultDialogFragment())
            }
//            viewModel.getSearchResult()
        }

        viewModel.getSearchResult()
        viewModel.response?.observe(viewLifecycleOwner, {
            Timber.i("Response: ${it.total_count}")
        })

        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "override fun onResume()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "override fun onDestroy()")
    }

    fun hideSoftKeyboard(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}