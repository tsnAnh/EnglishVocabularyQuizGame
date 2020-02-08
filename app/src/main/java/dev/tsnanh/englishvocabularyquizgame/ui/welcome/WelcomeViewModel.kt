package dev.tsnanh.englishvocabularyquizgame.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _navigateToPlay = MutableLiveData<Boolean>()
    val navigateToPlay: LiveData<Boolean>
        get() =_navigateToPlay

    private val _navigateToHighScore = MutableLiveData<Boolean>()
    val navigateToHighScore: LiveData<Boolean>
        get() = _navigateToHighScore

    fun onNavigateToHighScore() {
        _navigateToHighScore.value = true
    }

    fun onNavigatedToHighScore() {
        _navigateToHighScore.value = null
    }

    fun onNavigateToPlayFragment() {
        _navigateToPlay.value = true
    }

    fun onNavigatedToPlayFragment() {
        _navigateToPlay.value = null
    }
}
