package com.leadsdoit.quizforleadsdoit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * from questions ORDER BY id ASC")
    fun getAllQuestion(): Flow<List<Question>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question)

    // @Delete
    //suspend fun deleteAll()

    @Query("DELETE FROM questions")
    suspend fun deleteAll()
}