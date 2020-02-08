package dev.tsnanh.englishvocabularyquizgame.ui.high_score

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO
import java.lang.IllegalArgumentException

class HighScoreViewModelFactory(
    private val dataSource: VocabularyDAO, private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighScoreViewModel::class.java)) {
            return HighScoreViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
