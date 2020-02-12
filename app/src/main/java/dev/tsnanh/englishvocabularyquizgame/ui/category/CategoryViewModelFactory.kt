package dev.tsnanh.englishvocabularyquizgame.ui.category

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO

class CategoryViewModelFactory(
    private val dataSource: VocabularyDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}