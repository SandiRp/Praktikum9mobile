package com.example.praktikum9

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Word_table")
data class word (@PrimaryKey @ColumnInfo(name = "word")val word: String)