package kost.romi.repocommittimeline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kost.romi.repocommittimeline.databinding.FragmentMainBinding

/**
 * TODO: use <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>, to handle Oauth2.
 *
 * @see <a href="https://developer.android.com/training/id-auth/authenticate">Authenticate to OAuth2 services</a>
 */
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

}