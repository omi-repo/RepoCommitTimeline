package kost.romi.repocommittimeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.databinding.FragmentSearchResultDialogBinding

/**
 *
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

        binding.cancelButton.setOnClickListener {
//            dismiss()
            findNavController().navigate(SearchResultDialogFragmentDirections.actionSearchResultDialogFragmentToMainFragment2())
        }
    }

}