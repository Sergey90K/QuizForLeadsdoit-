package com.leadsdoit.quizforme.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Question::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var Instance: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, QuizDatabase::class.java, "quiz_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}