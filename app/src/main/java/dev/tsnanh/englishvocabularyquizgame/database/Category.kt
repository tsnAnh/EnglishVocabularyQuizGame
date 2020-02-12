package dev.tsnanh.englishvocabularyquizgame.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey
    var id: Long,
    var title: String?,
    var parent_id: Int?,
    var status: Int? = 1,
    var description: String?,
    var lft: Int?,
    var rgt: Int?,
    var intro_img: String?
)