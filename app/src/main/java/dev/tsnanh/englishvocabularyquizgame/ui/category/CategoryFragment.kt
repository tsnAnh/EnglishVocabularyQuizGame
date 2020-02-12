package dev.tsnanh.englishvocabularyquizgame.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyQuizDatabase
import dev.tsnanh.englishvocabularyquizgame.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_category, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = requireNotNull(activity!!).application
        val dataSource = VocabularyQuizDatabase.getInstance(application).dao
        val factory = CategoryViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        // TODO: Use the ViewModel
        val adapter = CategoryAdapter(CategoryListener { categoryID, imageView ->
            viewModel.onNavigateToListVocabulary(categoryID, imageView)
        })

        binding.listCategory.adapter = adapter
        binding.listCategory.layoutManager = LinearLayoutManager(this.context!!)
        binding.listCategory.setHasFixedSize(true)

        viewModel.categoriesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToListVocabulary.observe(viewLifecycleOwner, Observer {
            it?.let {
                val extras = FragmentNavigatorExtras(
                    it.second to it.first.intro_img!!
                )
                findNavController().navigate(
                    CategoryFragmentDirections
                        .actionNavigationCategoryToNavigationListVocabulary(
                            it.first.id, it.first.intro_img
                        ),
                    extras
                )
                viewModel.onNavigatedToListVocabulary()
            }
        })
    }

}
