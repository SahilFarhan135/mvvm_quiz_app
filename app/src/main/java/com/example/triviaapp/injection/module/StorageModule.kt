package com.example.triviaapp.injection.module

import android.content.Context
import androidx.room.Room
import com.example.triviaapp.db.QuizDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {


    @Singleton
    @Provides
    fun provideBaseAndroidDatabase(context: Context): QuizDatabase {
        return Room.databaseBuilder(context, QuizDatabase::class.java, "quizdatabase").build()
    }


}