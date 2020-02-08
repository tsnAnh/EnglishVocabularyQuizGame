package dev.tsnanh.englishvocabularyquizgame.ui.result

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import dev.tsnanh.englishvocabularyquizgame.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_result, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val playHistory = ResultFragmentArgs.fromBundle(arguments!!).playHistory
        val application = requireNotNull(activity!!).application

        val factory = ResultViewModelFactory(playHistory, application)

        viewModel = ViewModelProvider(this, factory).get(ResultViewModel::class.java)
        // TODO: Use the ViewModel

        binding.viewModel = viewModel

        viewModel.playHistoryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.playHistory = it
            }
        })

        viewModel.onUserTryAgain.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    ResultFragmentDirections.actionNavigationResultToNavigationPlay()
                )
                viewModel.onTryAgainClicked()
            }
        })

        viewModel.onUserReturnHome.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigateUp()
                viewModel.onHomeClicked()
            }
        })

        viewModel.onUserExit.observe(viewLifecycleOwner, Observer {
            it?.let {
                activity?.finish()
                viewModel.onExitClicked()
            }
        })

        viewModel.onUserShare.observe(viewLifecycleOwner, Observer {
            it?.let {
                onShareEvent(it)
            }
        })

        viewModel.onUserRate.observe(viewLifecycleOwner, Observer {
            it?.let {
                onRateEvent()
            }
        })
    }

    private fun onRateEvent() {
        val appPackageName: String? =
            activity?.packageName // getPackageName() from Context or Activity object

        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "https://play.google.com/store/apps/" +
                                "details?id=$appPackageName"
                    )
                )
            )
        }
        viewModel.onRateClicked()
    }

    private fun onShareEvent(it: Int) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "My score is $it. Can you beat me in this vocabulary game?\n" +
                        "Get it on Google Play: " +
                        "http://play.google.com/store/apps/details?" +
                        "id=dev.tsnanh.englishvocabularyquizgame"
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share your score!"))
        viewModel.onShareClicked()
    }

}
