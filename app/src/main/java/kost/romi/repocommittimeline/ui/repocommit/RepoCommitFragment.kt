package kost.romi.repocommittimeline.ui.repocommit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.animation.Stagger
import kost.romi.repocommittimeline.databinding.FragmentRepoCommitBinding
import kost.romi.repocommittimeline.ui.searchresult.CircleTransform

/**
 * TODO: finish adding RepoCommitAdapter.
 */
@AndroidEntryPoint
class RepoCommitFragment : Fragment() {

    private val TAG = "appDebugFragment"

    private lateinit var binding: FragmentRepoCommitBinding
    private val viewModel: RepoCommitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoCommitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: RepoCommitFragmentArgs by navArgs()
        val repoCommitUrl = args.repoCommitUrl
        val userName = args.userName
        val avatarUrl = args.avatarUrl

        // App bar
        Picasso.get().load(avatarUrl).transform(CircleTransform())
            .into(binding.appBarAvatarImageView)
        binding.appBarTitleTextView.text = "Commits  - ${userName}"

        viewModel.repoCommitUrl = repoCommitUrl
        viewModel.userName = userName

        viewModel.getRepoCommit()

        viewModel.getRepoCommitResponse.observe(viewLifecycleOwner, {
            if (it == GetRepoCommitResponse.SUCCESS) {
                Log.i(TAG, "onViewCreated: it == GetRepoCommitResponse.SUCCESS")
                binding.repoCommitProgressBar.visibility = View.INVISIBLE
                binding.repoCommitRecyclerView.visibility = View.VISIBLE
                // This is the transition for the stagger effect.
                val stagger = Stagger()
                // RecyclerView
                val adapter = viewModel.repoCommitResponse?.let { it1 ->
                    if (it1.size == null) {
                        RepoCommitAdapter(0)
                    } else {
                        RepoCommitAdapter(it1.size)
                    }
                }
                val recyclerView: RecyclerView = binding.repoCommitRecyclerView
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.recycledViewPool.setMaxRecycledViews(0, 0)
                recyclerView.adapter = adapter
                // Delay the stagger effect until the list is updated.
                TransitionManager.beginDelayedTransition(recyclerView, stagger)
                adapter?.submitList(viewModel.repoCommitResponse)
                recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

                }
            }
            if (it == GetRepoCommitResponse.FAIL) {
                Log.i(TAG, "onViewCreated: it == GetRepoCommitResponse.FAIL")
                binding.repoCommitProgressBar.visibility = View.INVISIBLE
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
            }
        })

    }

}