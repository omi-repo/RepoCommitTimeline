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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.SearchResponse
import kost.romi.repocommittimeline.SearchResultViewModel
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

        // RecyclerView
        val adapter = SearchResultRVAdapter()
        val recyclerView: RecyclerView = binding.searchUserRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        viewModel.getSearchResult(userName)

        // If HTTP request response success or fail.
        viewModel.searchResponse.observe(viewLifecycleOwner, {
            if (it == SearchResponse.SUCCESS) {
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                Log.i(TAG, "$it == success")
                binding.searchUserRecyclerView.visibility = View.VISIBLE
                adapter.submitList(viewModel.listUsersResponse?.items)
            }
            if (it == SearchResponse.FAIL) {
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                Log.i(TAG, "$it == fail")
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
            }
        })

        binding.cancelButton.setOnClickListener {
//            dismiss()
            findNavController().navigate(SearchResultDialogFragmentDirections.actionSearchResultDialogFragmentToMainFragment2())
        }
    }

}