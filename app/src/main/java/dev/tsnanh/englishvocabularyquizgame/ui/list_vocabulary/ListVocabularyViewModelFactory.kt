package dev.tsnanh.englishvocabularyquizgame.ui.list_vocabulary

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO

class ListVocabularyViewModelFactory(
    private val categoryID: Long,
    private val dataSource: VocabularyDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListVocabularyViewModel::class.java)) {
            return ListVocabularyViewModel(categoryID, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}