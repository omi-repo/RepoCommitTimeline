package kost.romi.repocommittimeline.ui.userrepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.R
import kost.romi.repocommittimeline.databinding.FragmentMainBinding
import kost.romi.repocommittimeline.databinding.FragmentUserRepoBinding
import kost.romi.repocommittimeline.ui.main.MainFragmentViewModel
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
        viewModel.getSearchResult()
        val userName = args.userName
        val avatarUrl = args.avatarUrl

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