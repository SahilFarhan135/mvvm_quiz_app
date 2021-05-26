package com.example.triviaapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Quiz(
    val userName: String,
    val bestCricketer: String,
    val colorsInNationlFlag: String,
    val dateTime: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}