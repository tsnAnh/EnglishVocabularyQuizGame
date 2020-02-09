package dev.tsnanh.englishvocabularyquizgame.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_welcome, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
        binding.viewModel = viewModel

        viewModel.navigateToPlay.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    WelcomeFragmentDirections.actionNavigationWelcomeToNavigationPlay()
                )
                viewModel.onNavigatedToPlayFragment()
            }
        })

        viewModel.navigateToHighScore.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    WelcomeFragmentDirections.actionNavigationWelcomeToNavigationHighScore()
                )
                viewModel.onNavigatedToHighScore()
            }
        })

        viewModel.navigateToSettings.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    WelcomeFragmentDirections.actionNavigationWelcomeToNavigationSettings()
                )
                viewModel.onNavigatedToSettings()
            }
        })

        viewModel.exitApp.observe(viewLifecycleOwner, Observer {
            it?.let {
                activity?.finish()
                viewModel.onExited()
            }
        })
    }

}
