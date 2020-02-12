package dev.tsnanh.englishvocabularyquizgame.database

import androidx.paging.DataSource
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

    @Query("SELECT * FROM categories WHERE intro_img NOT NULL order by title asc")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM vocabularies INNER JOIN categories INNER JOIN voc_cat where voc_cat.cat_id = categories.id and voc_cat.voc_id = vocabularies.id and categories.id = :categoryID")
    fun getVocabulariesInCategory(categoryID: Long): DataSource.Factory<Int, Vocabulary>

    @Query("SELECT * FROM vocabularies where id = :vocabularyID")
    fun getVocabulary(vocabularyID: Long): Vocabulary
}