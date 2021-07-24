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
 *
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

    var isLoading = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // args
        val args: RepoCommitFragmentArgs by navArgs()
        val repoCommitUrl = args.repoCommitUrl
        val userName = args.userName
        val avatarUrl = args.avatarUrl

        viewModel.repoCommitUrl = repoCommitUrl
        viewModel.userName = userName

        val adapter = RepoCommitAdapter()
        val recyclerView: RecyclerView = binding.repoCommitRecyclerView
        // This is the transition for the stagger effect.
        val stagger = Stagger()
        // RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.recycledViewPool.setMaxRecycledViews(0, 0)
        recyclerView.adapter = adapter
        // Delay the stagger effect until the list is updated.
        TransitionManager.beginDelayedTransition(recyclerView, stagger)

        viewModel.getRepoCommit()

        // If HTTP request response success or fail.
        viewModel.getRepoCommitResponse.observe(viewLifecycleOwner, {
            if (it == GetRepoCommitResponse.SUCCESS) {
                Log.i(TAG, "onViewCreated: it == GetRepoCommitResponse.SUCCESS")
                binding.repoCommitProgressBar.visibility = View.INVISIBLE
                binding.repoCommitRecyclerView.visibility = View.VISIBLE

                adapter?.submitList(viewModel.repoCommitResponse)
                adapter.notifyDataSetChanged()

                isLoading = false
                viewModel.page++
                binding.bottomUpdateProgressBar.visibility = View.GONE
            }
            if (it == GetRepoCommitResponse.FAIL) {
                Log.i(TAG, "onViewCreated: it == GetRepoCommitResponse.FAIL")
                binding.repoCommitProgressBar.visibility = View.INVISIBLE
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
                isLoading = false
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i(TAG, "onScrolled: HIT BOTTOM")
                    Log.i(TAG, "onScrolled: isLoading: $isLoading")
                    isLoading = true
                    viewModel.getNextRepoCommit()
                    binding.bottomUpdateProgressBar.visibility = View.VISIBLE
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

        // handle header for different page request.
//        viewModel.headerLink.observe(viewLifecycleOwner, {
//            if (it.contains("next")) {
//                Log.i(TAG, "NEXT: $it")
//                binding.nextPageFloatingActionButton.visibility = View.VISIBLE
//            } else {
//                binding.nextPageFloatingActionButton.visibility = View.INVISIBLE
//            }
//            if (it.contains("prev")) {
//                Log.i(TAG, "PREV: $it")
//                binding.prevPageFloatingActionButton.visibility = View.VISIBLE
//            } else {
//                binding.prevPageFloatingActionButton.visibility = View.INVISIBLE
//            }
//        })

        // App bar
        Picasso.get().load(avatarUrl).transform(CircleTransform())
            .into(binding.appBarAvatarImageView)
        binding.appBarTitleTextView.text = "Commits  - ${userName}"

    }

}