package dev.tsnanh.englishvocabularyquizgame.ui.high_score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyQuizDatabase
import dev.tsnanh.englishvocabularyquizgame.databinding.FragmentHighScoreBinding

class HighScoreFragment : Fragment() {

    private lateinit var viewModel: HighScoreViewModel
    private lateinit var binding: FragmentHighScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil
                .inflate(inflater, R.layout.fragment_high_score, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = requireNotNull(activity!!).application
        val dataSource = VocabularyQuizDatabase.getInstance(application).dao

        val factory = HighScoreViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, factory).get(HighScoreViewModel::class.java)
        // TODO: Use the ViewModel
        val adapter = HighScoreAdapter()
        binding.listHighScore.adapter = adapter
        binding.listHighScore.layoutManager = LinearLayoutManager(this.context)
        binding.listHighScore.setHasFixedSize(true)

        viewModel.playHistoryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

}
