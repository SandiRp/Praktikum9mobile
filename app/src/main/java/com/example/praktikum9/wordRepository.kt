package com.example.praktikum9

import androidx.lifecycle.LiveData

class wordRepository (private val worldDao: worldDao){
    val allWords: LiveData<List<word>> = worldDao.getAlphabetizedWords()
    suspend fun Insert(word: word){
        worldDao.Insert(word)
    }
}