package dev.tsnanh.englishvocabularyquizgame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Vocabulary::class, PlayHistory::class], version = 3, exportSchema = false)
abstract class VocabularyQuizDatabase : RoomDatabase() {

    abstract val dao: VocabularyDAO

    companion object {
        @Volatile
        private var INSTANCE: VocabularyQuizDatabase? = null

        fun getInstance(context: Context): VocabularyQuizDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance != null) {
                    return instance
                }
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    VocabularyQuizDatabase::class.java,
                    "picdic.db"
                ).createFromAsset("database/picdic.db").build()

                INSTANCE = instance
                return instance
            }
        }
    }

}