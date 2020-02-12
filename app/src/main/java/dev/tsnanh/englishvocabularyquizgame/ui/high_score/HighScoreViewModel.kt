package dev.tsnanh.englishvocabularyquizgame.ui.high_score

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HighScoreViewModel(
    private val dataSource: VocabularyDAO,
    application: Application
) : AndroidViewModel(application) {

    private val _playHistoryLiveData = MutableLiveData<ArrayList<PlayHistory>>(ArrayList())
    val playHistoryLiveData: LiveData<ArrayList<PlayHistory>>
        get() = _playHistoryLiveData

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _playHistoryLiveData.postValue(ArrayList(dataSource.getTopTenHighScore()))
            }
        }
    }
}
