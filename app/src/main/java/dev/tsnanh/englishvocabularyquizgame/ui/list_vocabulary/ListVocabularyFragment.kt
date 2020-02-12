package dev.tsnanh.englishvocabularyquizgame.ui.list_vocabulary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyQuizDatabase
import dev.tsnanh.englishvocabularyquizgame.databinding.FragmentListVocabularyBinding

class ListVocabularyFragment : Fragment() {

    private lateinit var binding: FragmentListVocabularyBinding
    private lateinit var viewModel: ListVocabularyViewModel
    private val args: ListVocabularyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_list_vocabulary, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ListVocabularyAdapter(ListVocabularyClickListener {
            viewModel.onNavigateToVocabulary(it)
        })

        binding.listVocabulary.adapter = adapter
        binding.listVocabulary.setHasFixedSize(true)
        binding.listVocabulary.isNestedScrollingEnabled = false
        binding.listVocabulary.layoutManager = LinearLayoutManager(this.context!!)

        val application = requireNotNull(activity!!).application
        val dataSource = VocabularyQuizDatabase.getInstance(application).dao
        val factory = ListVocabularyViewModelFactory(args.vocabularyID, dataSource, application)

        viewModel = ViewModelProvider(this, factory).get(ListVocabularyViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.vocabularyLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.forEach { vocabulary ->
                    Log.d("ListVocabularyFragment", "$vocabulary.en_us")
                }
                adapter.submitList(it)
            }
        })
    }

}
