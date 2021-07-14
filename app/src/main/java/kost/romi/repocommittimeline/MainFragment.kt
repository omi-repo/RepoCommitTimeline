package kost.romi.repocommittimeline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import kost.romi.repocommittimeline.databinding.FragmentMainBinding
import org.kohsuke.github.GHUserSearchBuilder
import org.kohsuke.github.GitHubBuilder
import timber.log.Timber

/**
 * TODO: use <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>, to handle Oauth2.
 *
 * @see <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
//            viewModel.searchThis = binding.searchEditText.text.toString()
            Toast.makeText(
                requireContext(),
                "${binding.searchEditText.text.toString()}",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.getSearchResult()
        viewModel.response?.observe(viewLifecycleOwner, {
            Timber.i("Response: ${it.total_count}")
        })
//        Timber.i("${viewModel.searchResult}")

//        val gitHub = GitHubBuilder().withOAuthToken(BuildConfig.Token).build()
//        Timber.i("NAME: ${gitHub.getUser("jake").name}")
//        Timber.i("GITHUB: ${gitHub.myself.repositories.size}")
    }

}