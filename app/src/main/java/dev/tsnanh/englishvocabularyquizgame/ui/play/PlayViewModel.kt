package dev.tsnanh.englishvocabularyquizgame.ui.play

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import dev.tsnanh.englishvocabularyquizgame.R
import dev.tsnanh.englishvocabularyquizgame.database.PlayHistory
import dev.tsnanh.englishvocabularyquizgame.database.Vocabulary
import dev.tsnanh.englishvocabularyquizgame.database.VocabularyDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class PlayViewModel(
    private val dataSource: VocabularyDAO,
    application: Application
) : AndroidViewModel(application) {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var sharedPref: SharedPreferences

    private val random = Random()
    private val context = (getApplication() as Application).applicationContext
    private var vocabularies: ArrayList<Vocabulary> = ArrayList()

    init {
        countDownTimer = object: CountDownTimer(1999L, 1000L) {
            override fun onFinish() {
                _onGameFinished.value = recordNewPlayHistory()
                countDownTimer.cancel()
            }

            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished
                Log.d("PlayViewModel", "$millisUntilFinished")
            }
        }
        viewModelScope.launch {
            vocabularies = getVocabularies()
            if (generateAnswer()) {
                _currentVocabulary.postValue(vocabularies[0])
                _currentAnswer.postValue(vocabularies[0].en_us)
            } else {
                _currentVocabulary.postValue(vocabularies[random.nextInt(vocabularies.size)])
                _currentAnswer.postValue(vocabularies[random.nextInt(vocabularies.size)].en_us)
            }
//            startTimer()
            preloadImage(vocabularies[_numberCorrect.value!! + 1])
        }
//        sharedPref = context.getSharedPreferences(
//            context.getString(R.string.shared_preferences_key),
//            Context.MODE_PRIVATE
//        )
    }

    private suspend fun getVocabularies(): ArrayList<Vocabulary> {
        return withContext(Dispatchers.IO) {
            ArrayList(dataSource.getAllVocabulary().toMutableList().apply {
                this.shuffle()
            })
        }
    }

    private val _numberCorrect = MutableLiveData(0)
    val numberCorrect: LiveData<Int>
        get() = _numberCorrect

    private val _currentVocabulary = MutableLiveData<Vocabulary>()
    val currentVocabulary: LiveData<Vocabulary>
        get() = _currentVocabulary

    private val _currentAnswer =
        MutableLiveData<String>()
    val currentAnswer: LiveData<String>
        get() = _currentAnswer

    private val _onGameFinished = MutableLiveData<PlayHistory>()
    val onGameFinished: LiveData<PlayHistory>
        get() = _onGameFinished

    private val _timeLeft = MutableLiveData<Long>(-1L)
    val timeLeft: LiveData<String>
        get() = Transformations.map(_timeLeft, Function { millis ->
            return@Function if (millis != -1L) {
                ((millis + 1000) / 1000).toInt().toString()
            } else {
                "Waiting for image.."
            }
        })

    private fun generateAnswer(): Boolean {
        return random.nextBoolean()
    }

    private fun getWrongAnswer(vocabularyIndex: Int): String {
        var index: Int

        do {
            index = random.nextInt(vocabularies.size)
        } while (index == vocabularyIndex)

        return vocabularies[index].en_us.toString()
    }

    private fun recordNewPlayHistory(): PlayHistory {
        val playHistory = PlayHistory(
            null,
            _numberCorrect.value!!,
            Date().toString(),
            0,
            "CC NE",
            getScoreRating(_numberCorrect.value!!)
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dataSource.insertNewScore(playHistory)
            }
        }
        return playHistory
    }

    private fun getScoreRating(value: Int): Int {
        return when {
            value <= 10 -> {
                R.drawable.ic_sentiment_very_dissatisfied_black_24dp
            }
            value in 10..20 -> {
                R.drawable.ic_sentiment_dissatisfied_black_24dp
            }
            value in 20..30 -> {
                R.drawable.ic_sentiment_neutral_black_24dp
            }
            value in 30..40 -> {
                R.drawable.ic_sentiment_satisfied_black_24dp
            }
            else -> {
                R.drawable.ic_sentiment_very_satisfied_black_24dp
            }
        }
    }

    private fun finishGame() {
        _onGameFinished.value = recordNewPlayHistory()
    }

    fun onFinishedGame() {
        _onGameFinished.value = null
    }

    fun onCorrectClick() {
        countDownTimer.cancel()
        if (_currentAnswer.value == _currentVocabulary.value?.en_us) {
            nextVocabulary()
        } else {
            finishGame()
        }
    }

    fun onWrongClick() {
        countDownTimer.cancel()
        if (_currentAnswer.value != _currentVocabulary.value?.en_us) {
            nextVocabulary()
        } else {
            finishGame()
        }
    }

    private fun nextVocabulary() {
        _numberCorrect.value = _numberCorrect.value!! + 1
        _currentVocabulary.value = vocabularies[_numberCorrect.value!!]
        _currentAnswer.value = if (generateAnswer()) {
            vocabularies[_numberCorrect.value!!].en_us
        } else {
            getWrongAnswer(numberCorrect.value!!)
        }
//        startTimer()
        preloadImage(vocabularies[_numberCorrect.value!!])
        _timeLeft.value = -1L
    }

    private fun preloadImage(vocabulary: Vocabulary) {
        Glide
            .with((this.getApplication() as Application).applicationContext)
            .load(
                (getApplication() as Application).applicationContext
                .getString(R.string.api_image_url) + vocabulary.image
            )
            .preload()
    }

    fun startTimer() {
//        val duration = sharedPref.getInt(context.getString(R.string.difficulty), 4_000)
        countDownTimer.start()
    }

    override fun onCleared() {
        super.onCleared()

        countDownTimer.cancel()
    }
}
