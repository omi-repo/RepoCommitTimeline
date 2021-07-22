package kost.romi.repocommittimeline.ui.searchresult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
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

    var isLoading = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // args
        val args: SearchResultDialogFragmentArgs by navArgs()
        val userName = args.userName

        val recyclerView: RecyclerView = binding.searchUserRecyclerView
        val adapter = SearchResultRVAdapter()
        // This is the transition for the stagger effect.
        val stagger = Stagger()
        // RecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
        // Delay the stagger effect until the list is updated.
        TransitionManager.beginDelayedTransition(recyclerView, stagger)

        viewModel.getSearchResult(userName)

        // If HTTP request response success or fail.
        viewModel.searchResponse.observe(viewLifecycleOwner, {
            if (it == SearchResponse.SUCCESS) {
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                Log.i(TAG, "$it == success")
                binding.searchUserRecyclerView.visibility = View.VISIBLE

                adapter.submitList(viewModel.listUsersResponse)
                adapter.notifyDataSetChanged()

                isLoading = false
                viewModel.page++
                binding.loadMoreFromBottomProgressBar.visibility = View.GONE
            }
            if (it == SearchResponse.FAIL) {
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                Log.i(TAG, "$it == fail")
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
                isLoading = false
            }
        })

        // handle listener when recyclerview reach bottom
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i(TAG, "onScrolled: HIT BOTTOM")
                    Log.i(TAG, "onScrolled: isLoading: $isLoading")
                    isLoading = true
                    viewModel.getNextSearchResult(userName)
                    binding.loadMoreFromBottomProgressBar.visibility = View.VISIBLE
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                if (!recyclerView.canScrollHorizontally(1)) {
//                    Log.i(TAG, "onScrolled: HIT BOTTOM")
//                    Log.i(TAG, "onScrolled: isLoading: $isLoading")
//                    isLoading = true
//                }
//                if (!recyclerView.canScrollHorizontally(-1)) {
//                    Log.i(TAG, "onScrolled: scrolled up")
//                    Log.i(TAG, "onScrolled: isLoading: $isLoading")
//                    isLoading = false
//                }
            }
        })

        binding.cancelButton.setOnClickListener {
//            dismiss()
            findNavController().navigate(SearchResultDialogFragmentDirections.actionSearchResultDialogFragmentToMainFragment2())
        }
    }

}