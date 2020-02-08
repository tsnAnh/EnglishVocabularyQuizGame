package dev.tsnanh.englishvocabularyquizgame.ui.play

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyQuizDatabase
import dev.tsnanh.englishvocabularyquizgame.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel
    private lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = requireNotNull(activity!!).application
        val dataSource = VocabularyQuizDatabase.getInstance(application).dao
        val factory = PlayViewModelFactory(dataSource, application)
        playViewModel = ViewModelProvider(this, factory).get(PlayViewModel::class.java)

        binding.viewModel = playViewModel

        playViewModel.currentAnswer.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.currentAnswer = it
            }
        })

        playViewModel.currentVocabulary.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.currentVocabulary = it
                binding.executePendingBindings()
            }
        })

        playViewModel.onGameFinished.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(PlayFragmentDirections
                    .actionNavigationPlayToNavigationResult(it))
                playViewModel.onFinishedGame()
            }
        })

        playViewModel.numberCorrect.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.score.text = "Your Score: $it"
            }
        })

        playViewModel.timeLeft.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.timeLeft.text = it
            }
        })
    }

}
