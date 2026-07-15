package com.example.v.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.v.data.model.Table
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("Select * from notes Where category = 'Basket'")
    fun getBasket(): PagingSource<Int, Table>

    @Query("Select * from notes Where category = 'Main'")
    fun getMain(): PagingSource<Int, Table>

    @Query("Select * from notes Where category = :category")
    fun getOther(category: String): PagingSource<Int, Table>

    @Query("Select * from notes Where id = :id")
    suspend fun getById(id: Int): Table
    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insertNote(note: Table)

    @Delete(
    )
    suspend fun deleteNote(note: Table)

    @Update
    suspend fun updateNote(note: Table)

}