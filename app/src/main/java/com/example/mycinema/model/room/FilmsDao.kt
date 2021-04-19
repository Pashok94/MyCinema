package com.example.mycinema.model.room

import androidx.room.*
import com.example.mycinema.model.Result

@Dao
interface FilmsDao {
    @Query("SELECT * FROM FilmsEntity")
    fun getAll(): List<FilmsEntity>

    @Query("SELECT * FROM FilmsEntity WHERE id LIKE :filmsId")
    fun getFilmById(filmsId: String): Result

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FilmsEntity)

    @Update
    fun update(entity: FilmsEntity)

    @Delete
    fun delete(entity: FilmsEntity)
}