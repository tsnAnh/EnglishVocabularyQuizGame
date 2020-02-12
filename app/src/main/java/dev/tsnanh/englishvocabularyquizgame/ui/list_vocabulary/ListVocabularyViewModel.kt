package dev.tsnanh.englishvocabularyquizgame.ui.list_vocabulary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO

class ListVocabularyViewModel(
    private val categoryID: Long,
    private val dataSource: VocabularyDAO,
    application: Application
) : AndroidViewModel(application) {
    //    private val _vocabulariesLiveData = MutableLiveData<PagedList<Vocabulary>>()
    val vocabularyLiveData =
        dataSource.getVocabulariesInCategory(categoryID).toLiveData(pageSize = 10)

    private val _navigateToVocabulary = MutableLiveData<Long>()
    val navigateToVocabulary: LiveData<Long>
        get() = _navigateToVocabulary

    fun onNavigateToVocabulary(id: Long) {
        _navigateToVocabulary.value = id
    }

    fun onNavigatedToVocabulary() {
        _navigateToVocabulary.value = null
    }

//    init {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                _vocabulariesLiveData
//            }
//        }
//    }
}
