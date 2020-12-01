package com.example.praktikum9

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(word::class),version = 1, exportSchema = false)
public abstract class wordRoomDatabase : RoomDatabase(){
    abstract fun worldDao():worldDao

    private class  WordDataBaseCallBack (
        private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.worldDao())
                }
            }
        }
        suspend fun populateDatabase(worldDao: worldDao){
            worldDao.deleteAll()

            var Word = word("Hello")
            worldDao.Insert(Word)
            Word = word("Semua")
            worldDao.Insert(Word)
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: wordRoomDatabase? = null

        fun getDatabase (context: Context, scope: CoroutineScope):wordRoomDatabase{
            return INSTANCE ?: synchronized(this   ) {


                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext, wordRoomDatabase::class.java,
                        "word_database"
                    )
                        .addCallback(WordDataBaseCallBack(scope))
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}

