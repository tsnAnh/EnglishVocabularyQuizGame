package dev.tsnanh.englishvocabularyquizgame.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VocabularyDAO {
    @Query("SELECT * FROM VOCABULARIES WHERE en_us is not null and image is not null")
    fun getAllVocabulary(): List<Vocabulary>

    @Query("SELECT * FROM play_history order by correctQuestion desc limit 10")
    fun getTopTenHighScore(): List<PlayHistory>

    @Insert(entity = PlayHistory::class)
    fun insertNewScore(playHistory: PlayHistory)
}