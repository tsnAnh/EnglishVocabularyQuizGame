package dev.tsnanh.englishvocabularyquizgame.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabularies")
data class Vocabulary(
    @PrimaryKey
    var id: Long,
    var image: String?,
    var en_us: String?,
    var en_us_type: String?,
    var en_us_pr: String?,
    var en_us_audio: String?,
    var en_us_mean: String?,
    var en_us_ex: String?,
    var vi_vn: String?,
    var es_es: String?
)