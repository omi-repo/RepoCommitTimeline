package kost.romi.repocommittimeline.ui.searchresult

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.SearchResponse
import kost.romi.repocommittimeline.SearchResultViewModel
import kost.romi.repocommittimeline.animation.Stagger
import kost.romi.repocommittimeline.databinding.FragmentSearchResultDialogBinding

/**
 * TODO: add RecyclerView to success response.
 */

@AndroidEntryPoint
class SearchResultDialogFragment : BottomSheetDialogFragment() {

    private val TAG = "appDebugFragment"

    private lateinit var binding: FragmentSearchResultDialogBinding
    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // args
        val args: SearchResultDialogFragmentArgs by navArgs()
        val userName = args.userName

        val recyclerView: RecyclerView = binding.searchUserRecyclerView
        val adapter = SearchResultRVAdapter()

        viewModel.getSearchResult(userName)

        // If HTTP request response success or fail.
        viewModel.searchResponse.observe(viewLifecycleOwner, {
            if (it == SearchResponse.SUCCESS) {
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                Log.i(TAG, "$it == success")
                binding.searchUserRecyclerView.visibility = View.VISIBLE
                // This is the transition for the stagger effect.
                val stagger = Stagger()
                // RecyclerView
                recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                recyclerView.adapter = adapter
                // Delay the stagger effect until the list is updated.
                TransitionManager.beginDelayedTransition(recyclerView, stagger)
                adapter.submitList(viewModel.listUsersResponse?.items)
            }
            if (it == SearchResponse.FAIL) {
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                Log.i(TAG, "$it == fail")
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
            }
        })

        // handle header for different page request.
        viewModel.headerLink.observe(viewLifecycleOwner, {
            if (it.contains("next")) {
                Log.i(TAG, "NEXT: $it")
                binding.nextPageFloatingActionButton.visibility = View.VISIBLE
            } else {
                binding.nextPageFloatingActionButton.visibility = View.INVISIBLE
            }
            if (it.contains("prev")) {
                Log.i(TAG, "PREV: $it")
                binding.prevPageFloatingActionButton.visibility = View.VISIBLE
            } else {
                binding.prevPageFloatingActionButton.visibility = View.INVISIBLE
            }
        })

        binding.nextPageFloatingActionButton.setOnClickListener {
            viewModel.page++
            adapter.submitList(null)
            binding.searchUserRecyclerView.visibility = View.INVISIBLE
            binding.searchUserProgressBar.visibility = View.VISIBLE
            viewModel.getNextSearchResult(userName)
        }

        binding.prevPageFloatingActionButton.setOnClickListener {
            viewModel.page--
            adapter.submitList(null)
            binding.searchUserRecyclerView.visibility = View.INVISIBLE
            binding.searchUserProgressBar.visibility = View.VISIBLE
            viewModel.getPrevSearchResult(userName)
        }

        binding.cancelButton.setOnClickListener {
//            dismiss()
            findNavController().navigate(SearchResultDialogFragmentDirections.actionSearchResultDialogFragmentToMainFragment2())
        }
    }

}