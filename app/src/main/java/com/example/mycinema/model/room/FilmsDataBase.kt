package com.example.mycinema.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FilmsEntity::class], version = 1, exportSchema = false)
abstract class FilmsDataBase : RoomDatabase() {
    abstract fun filmsDao() : FilmsDao
}