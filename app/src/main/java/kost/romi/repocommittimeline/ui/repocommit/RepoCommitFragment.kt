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
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.databinding.FragmentRepoCommitBinding
import kost.romi.repocommittimeline.databinding.FragmentUserRepoBinding
import kost.romi.repocommittimeline.ui.searchresult.CircleTransform
import kost.romi.repocommittimeline.ui.userrepo.GetUserRepoResponse
import kost.romi.repocommittimeline.ui.userrepo.UserRepoRVAdapter

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

    // RecyclerView
    val adapter = UserRepoRVAdapter()
    val recyclerView: RecyclerView = binding.repoCommitRecyclerView
    recyclerView.layoutManager = LinearLayoutManager(requireContext())
    recyclerView.adapter = adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: RepoCommitFragmentArgs by navArgs()
        val repoCommitUrl = args.repoCommitUrl
        val userName = args.userName
        val avatarUrl = args.avatarUrl

        // App bar
        Picasso.get().load(avatarUrl).transform(CircleTransform())
            .into(binding.appBarAvatarImageView)
        binding.appBarTitleTextView.text = "Commit timeline - ${userName}"

        viewModel.repoCommitUrl = repoCommitUrl
        viewModel.userName = userName

        viewModel.getRepoCommit()

        viewModel.getRepoCommitResponse.observe(viewLifecycleOwner, {
            binding.repoCommitProgressBar.visibility = View.GONE
            if (it == GetRepoCommitResponse.SUCCESS) {
                Log.i(TAG, "onViewCreated: it == GetRepoCommitResponse.SUCCESS")
                binding.repoCommitRecyclerView.visibility = View.VISIBLE
                adapter.submitList(viewModel.listUsersResponse)
            }
            if (it == GetRepoCommitResponse.FAIL) {
                Log.i(TAG, "onViewCreated: it == GetRepoCommitResponse.FAIL")
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
            }
        })

    }

}