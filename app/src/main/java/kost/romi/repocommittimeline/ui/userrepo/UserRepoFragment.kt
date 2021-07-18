package kost.romi.repocommittimeline.ui.userrepo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.databinding.FragmentMainBinding
import kost.romi.repocommittimeline.databinding.FragmentUserRepoBinding
import kost.romi.repocommittimeline.ui.main.MainFragmentViewModel
import kost.romi.repocommittimeline.ui.searchresult.CircleTransform
import kost.romi.repocommittimeline.ui.searchresult.SearchResultRVAdapter

/**
 *
 */

@AndroidEntryPoint
class UserRepoFragment : Fragment() {

    private val TAG = "appDebugFragment"

    private lateinit var binding: FragmentUserRepoBinding
    private val viewModel: UserRepoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: UserRepoFragmentArgs by navArgs()
        val userRepoUrl = args.userRepoUrl
        viewModel.userRepoUrl = userRepoUrl
        viewModel.getSearchResult()
        val userName = args.userName
        val avatarUrl = args.avatarUrl

        // RecyclerView
        val adapter = UserRepoRVAdapter()
        val recyclerView: RecyclerView = binding.userRepoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.getUserRepoResponse.observe(viewLifecycleOwner, {
            if (it == GetUserRepoResponse.SUCCESS) {
                Log.i(TAG, "onViewCreated: it == GetUserRepoResponse.SUCCESS")
                adapter.submitList(viewModel.listUsersResponse)
            }
            if (it == GetUserRepoResponse.FAIL) {
                Log.i(TAG, "onViewCreated: it == GetUserRepoResponse.FAIL")
            }
        })

        Picasso.get().load(avatarUrl).transform(CircleTransform())
            .into(binding.appBarAvatarImageView)
        binding.appBarTitleTextView.text = userName

        Toast.makeText(
            requireContext(),
            "This is the link to User Repo: ${userRepoUrl}",
            Toast.LENGTH_SHORT
        ).show()

    }

}