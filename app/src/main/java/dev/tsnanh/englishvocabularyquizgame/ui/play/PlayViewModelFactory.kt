package dev.tsnanh.englishvocabularyquizgame.ui.play

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO

class PlayViewModelFactory(
    private val dataSource: VocabularyDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayViewModel::class.java)) {
            return PlayViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}