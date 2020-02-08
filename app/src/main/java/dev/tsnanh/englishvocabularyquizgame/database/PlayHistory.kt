package dev.tsnanh.englishvocabularyquizgame.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "play_history")
data class PlayHistory(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var correctQuestion: Int,
    var timeEnd: String,
    var level: Int,
    var playerName: String,
    var scoreRating: Int
) : Parcelable