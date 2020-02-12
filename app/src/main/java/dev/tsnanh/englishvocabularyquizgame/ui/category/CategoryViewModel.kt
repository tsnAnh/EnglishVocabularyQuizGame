package dev.tsnanh.englishvocabularyquizgame.ui.category

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.tsnanh.englishvocabularyquizgame.database.Category
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(
    private val dataSource: VocabularyDAO,
    application: Application
) : AndroidViewModel(application) {
    private val categories: ArrayList<Category> = ArrayList()

    private val _categoriesLiveData = MutableLiveData<ArrayList<Category>>(categories)
    val categoriesLiveData: LiveData<ArrayList<Category>>
        get() = _categoriesLiveData

    private val _navigateToListVocabulary = MutableLiveData<Pair<Category, ImageView>>()
    val navigateToListVocabulary: LiveData<Pair<Category, ImageView>>
        get() = _navigateToListVocabulary

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _categoriesLiveData.postValue(ArrayList(dataSource.getAllCategories()))
            }
        }
    }

    fun onNavigateToListVocabulary(category: Category, imageView: ImageView) {
        _navigateToListVocabulary.value = Pair(category, imageView)
    }

    fun onNavigatedToListVocabulary() {
        _navigateToListVocabulary.value = null
    }
}
