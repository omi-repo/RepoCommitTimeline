package kost.romi.repocommittimeline.ui.repocommit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.R

/**
 *
 */
@AndroidEntryPoint
class RepoCommitFragment : Fragment() {


    private val viewModel: RepoCommitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_commit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: RepoCommitFragmentArgs by navArgs()
        val repoCommitUrl = args.repoCommitUrl
        val userName = args.userName
        viewModel.repoCommitUrl = repoCommitUrl
        viewModel.userName = userName

        viewModel.getRepoCommit()

    }

}