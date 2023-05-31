package com.example.heo_di_hoc

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Entity(tableName = "quizzes")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)

@Entity(
    tableName = "quiz_sets",
    foreignKeys = [
        ForeignKey(
            entity = Quiz::class,
            parentColumns = ["id"],
            childColumns = ["quiz_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuizSet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "quiz_id", index = true) val quizId: Int,
    val question: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val correctAnswer: Int
)

@Dao
interface QuizDao {
    @Insert
    suspend fun insertQuiz(quiz: Quiz): Long

    @Insert
    suspend fun insertQuizSet(quizSet: QuizSet): Long

    @Transaction
    @Query("SELECT * FROM quizzes WHERE id = :quizId")
    suspend fun getQuizWithQuizSets(quizId: Int): QuizWithQuizSets
}


data class QuizWithQuizSets(
    @Embedded val quiz: Quiz,
    @Relation(
        parentColumn = "id",
        entityColumn = "quiz_id"
    )
    val quizSets: List<QuizSet>
)

@Database(entities = [Quiz::class, QuizSet::class], version = 1)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var instance: QuizDatabase? = null
        fun getDatabase(context: Context): QuizDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Insert default quizzes and quiz sets here
                            CoroutineScope(Dispatchers.IO).launch {
                                val quizDao = getDatabase(context).quizDao()

                                val quiz1 = Quiz(id = 1, name = "Quiz 1")
                                val quiz2 = Quiz(id = 2, name = "Quiz 2")

                                val quiz1Id = quizDao.insertQuiz(quiz1)
                                val quiz2Id = quizDao.insertQuiz(quiz2)

                                val quizSet1 = QuizSet(
                                    quizId = quiz1Id.toInt(),
                                    question = "Question 1",
                                    answer1 = "Answer 1",
                                    answer2 = "Answer 2",
                                    answer3 = "Answer 3",
                                    answer4 = "Answer 4",
                                    correctAnswer = 1
                                )

                                val quizSet2 = QuizSet(
                                    quizId = quiz2Id.toInt(),
                                    question = "Question 2",
                                    answer1 = "Answer 1",
                                    answer2 = "Answer 2",
                                    answer3 = "Answer 3",
                                    answer4 = "Answer 4",
                                    correctAnswer = 2
                                )

                                quizDao.insertQuizSet(quizSet1)
                                quizDao.insertQuizSet(quizSet2)
                            }
                        }
                    })
                    .build()
                instance = newInstance
                newInstance
            }
        }
    }
}

suspend fun getRandomQuizSetFromDatabase(context: Context, quizId: Int): QuizSet? {
    val quizWithQuizSets = QuizDatabase.getDatabase(context).quizDao().getQuizWithQuizSets(quizId)
    return quizWithQuizSets.quizSets.shuffled().firstOrNull()
}
