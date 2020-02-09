package dev.tsnanh.englishvocabularyquizgame.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory

class ResultViewModel(
    private val playHistory: PlayHistory,
    application: Application
) : AndroidViewModel(application) {

    private val _playHistoryLiveData = MutableLiveData<PlayHistory>(playHistory)
    val playHistoryLiveData: LiveData<PlayHistory>
        get() = _playHistoryLiveData

    private val _onUserTryAgain = MutableLiveData<Boolean>()
    val onUserTryAgain: LiveData<Boolean>
        get() = _onUserTryAgain

    private val _onUserReturnHome = MutableLiveData<Boolean>()
    val onUserReturnHome: LiveData<Boolean>
        get() = _onUserReturnHome

    private val _onUserShare = MutableLiveData<Int>()
    val onUserShare: LiveData<Int>
        get() = _onUserShare

    private val _onUserRate = MutableLiveData<Boolean>()
    val onUserRate: LiveData<Boolean>
        get() = _onUserRate

    fun onTryAgainClick() {
        _onUserTryAgain.value = true
    }

    fun onTryAgainClicked() {
        _onUserTryAgain.value = null
    }

    fun onHomeClick() {
        _onUserReturnHome.value = true
    }

    fun onHomeClicked() {
        _onUserReturnHome.value = null
    }

    fun onShareClick() {
        _onUserShare.value = playHistory.correctQuestion
    }

    fun onShareClicked() {
        _onUserShare.value = null
    }

    fun onRateClick() {
        _onUserRate.value = true
    }

    fun onRateClicked() {
        _onUserRate.value = false
    }
}
