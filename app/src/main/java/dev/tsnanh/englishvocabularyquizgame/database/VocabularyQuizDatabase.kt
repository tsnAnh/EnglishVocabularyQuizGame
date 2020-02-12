package dev.tsnanh.englishvocabularyquizgame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Vocabulary::class, PlayHistory::class, Category::class, VocCat::class],
    version = 4,
    exportSchema = false
)
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
                )
                    .addMigrations(object : Migration(3, 4) {
                        override fun migrate(database: SupportSQLiteDatabase) {

                        }
                    }).createFromAsset("database/picdic.db").build()

                INSTANCE = instance
                return instance
            }
        }
    }

}