package com.example.praktikum9

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class wordViewModel (application: Application) : AndroidViewModel(application){
    private val repository: wordRepository
    val allWords: LiveData<List<word>>

    init {
        val worldDao = wordRoomDatabase.getDatabase(application, viewModelScope).worldDao()
        repository = wordRepository(worldDao)
        allWords = repository.allWords
    }

    fun insert(word: word) = viewModelScope.launch {
        repository.Insert(word)
    }


}