package kost.romi.repocommittimeline.ui.userrepo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.animation.Stagger
import kost.romi.repocommittimeline.databinding.FragmentUserRepoBinding
import kost.romi.repocommittimeline.ui.searchresult.CircleTransform


/**
 *
 */

@AndroidEntryPoint
class UserRepoFragment : Fragment() {

    private val TAG = "appDebugFragment"

    private lateinit var binding: FragmentUserRepoBinding
    private val viewModel: UserRepoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(
                        UserRepoFragmentDirections.actionUserRepoFragmentToMainFragment()
                    )
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // args
        val args: UserRepoFragmentArgs by navArgs()
        val userRepoUrl = args.userRepoUrl
        viewModel.userRepoUrl = userRepoUrl
        val userName = args.userName
        val avatarUrl = args.avatarUrl

        val adapter = UserRepoRVAdapter()
        val recyclerView: RecyclerView = binding.userRepoRecyclerView

        viewModel.getUserRepo()

        // If HTTP request response success or fail.
        viewModel.getUserRepoResponse.observe(viewLifecycleOwner, {
            if (it == GetUserRepoResponse.SUCCESS) {
                Log.i(TAG, "onViewCreated: it == GetUserRepoResponse.SUCCESS")
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                binding.userRepoRecyclerView.visibility = View.VISIBLE
                // This is the transition for the stagger effect.
                val stagger = Stagger()
                // RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = adapter
                // Delay the stagger effect until the list is updated.
                TransitionManager.beginDelayedTransition(recyclerView, stagger)
                adapter.submitList(viewModel.listUsersResponse)
            }
            if (it == GetUserRepoResponse.FAIL) {
                Log.i(TAG, "onViewCreated: it == GetUserRepoResponse.FAIL")
                binding.searchUserProgressBar.visibility = View.INVISIBLE
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
            binding.userRepoRecyclerView.visibility = View.INVISIBLE
            binding.searchUserProgressBar.visibility = View.VISIBLE
            viewModel.getNextUserRepo()
        }

        binding.prevPageFloatingActionButton.setOnClickListener {
            viewModel.page--
            adapter.submitList(null)
            binding.userRepoRecyclerView.visibility = View.INVISIBLE
            binding.searchUserProgressBar.visibility = View.VISIBLE
            viewModel.getPervUserRepo()
        }

        // App bar
        Picasso.get().load(avatarUrl).transform(CircleTransform())
            .into(binding.appBarAvatarImageView)
        binding.appBarTitleTextView.text = "Repo - ${userName}"

    }

}