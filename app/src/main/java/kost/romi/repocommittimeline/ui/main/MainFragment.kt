package kost.romi.repocommittimeline.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.databinding.FragmentMainBinding


/**
 *
 * @see <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>
 */

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val TAG = "appDebugFragment"

    private var binding: FragmentMainBinding? = null
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    val startMain = Intent(Intent.ACTION_MAIN)
                    startMain.addCategory(Intent.CATEGORY_HOME)
                    startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(startMain)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "override fun onViewCreated(view: View, savedInstanceState: Bundle?)")

        //
        binding?.searchEditText?.setText(viewModel.userNameEditText.value)
        binding?.searchEditText?.doOnTextChanged { text, start, before, count ->
            viewModel.onUserNameChange(text.toString())
        }

        // Handle Search Button.
        binding?.searchButton?.setOnClickListener {
            hideSoftKeyboard(requireView())
            if (binding?.searchEditText?.text!!.isEmpty()) {
                Snackbar
                    .make(
                        binding?.mainContainer!!,
                        "Search bar can't be empty",
                        Snackbar.LENGTH_LONG
                    )
                    .setAnchorView(binding?.bottomAppBar)
                    .show()
            } else {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToSearchResultDialogFragment(
                        binding?.searchEditText?.text.toString()
                    )
                )
            }
        }

        binding?.searchEditText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding?.searchEditText!!.text.isEmpty()) {
                    Snackbar
                        .make(
                            binding?.mainContainer!!,
                            "Search bar can't be empty",
                            Snackbar.LENGTH_LONG
                        )
                        .setAnchorView(binding?.bottomAppBar)
                        .show()
                } else {
                    findNavController().navigate(
                        MainFragmentDirections.actionMainFragmentToSearchResultDialogFragment(
                            binding?.searchEditText!!.text.toString()
                        )
                    )
                }
                true
            }
            false
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "override fun onDestroyView()")
        binding = null
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