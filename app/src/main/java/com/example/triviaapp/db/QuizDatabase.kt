package com.example.triviaapp.db

import android.content.Context
import android.os.Build
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.triviaapp.db.QuizDatabase.Companion.VERSION


@Database(
    entities = [Quiz::class],
    version = VERSION
)

abstract class QuizDatabase : RoomDatabase() {

    abstract fun getQuizDao(): QuizDao


    companion object {

        const val NAME = "quizdatabase"
        const val VERSION = 1

        @Volatile
        private var INSTANCE: QuizDatabase? = null
        fun getInstance(context: Context): QuizDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    QuizDatabase::class.java,
                    NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}