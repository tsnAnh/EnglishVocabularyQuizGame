package dev.tsnanh.englishvocabularyquizgame.ui.result

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory
import java.lang.IllegalArgumentException

class ResultViewModelFactory(
    private val playHistory: PlayHistory,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(playHistory, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}