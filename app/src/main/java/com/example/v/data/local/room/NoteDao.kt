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
    @Query("Select * from notes")
    fun getInfo(): PagingSource<Int, Table>

    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insertNote(note: Table)

    @Delete(
    )
    suspend fun deleteNote(note: Table)

    @Update(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun updateNote(note: Table)

}