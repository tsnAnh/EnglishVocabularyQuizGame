package dev.tsnanh.englishvocabularyquizgame.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voc_cat")
data class VocCat(
    @PrimaryKey
    var id: Long,
    var voc_id: Long?,
    var cat_id: Long?
)