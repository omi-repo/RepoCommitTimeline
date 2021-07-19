package kost.romi.repocommittimeline.ui.userrepo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
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
        val userName = args.userName
        val avatarUrl = args.avatarUrl

        viewModel.getUserRepo()

        // RecyclerView
        val adapter = UserRepoRVAdapter()
        val recyclerView: RecyclerView = binding.userRepoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.getUserRepoResponse.observe(viewLifecycleOwner, {
            if (it == GetUserRepoResponse.SUCCESS) {
                Log.i(TAG, "onViewCreated: it == GetUserRepoResponse.SUCCESS")
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                binding.userRepoRecyclerView.visibility = View.VISIBLE
                adapter.submitList(viewModel.listUsersResponse)
            }
            if (it == GetUserRepoResponse.FAIL) {
                Log.i(TAG, "onViewCreated: it == GetUserRepoResponse.FAIL")
                binding.searchUserProgressBar.visibility = View.INVISIBLE
                binding.searchUserFailResponseTextView.visibility = View.VISIBLE
            }
        })

        // App bar
        Picasso.get().load(avatarUrl).transform(CircleTransform())
            .into(binding.appBarAvatarImageView)
        binding.appBarTitleTextView.text = "Repo - ${userName}"

//        Toast.makeText(
//            requireContext(),
//            "This is the link to User Repo: ${userRepoUrl}",
//            Toast.LENGTH_SHORT
//        ).show()

    }

}