package com.example.v.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.v.data.local.relation.FolderWithNote
import com.example.v.data.local.entity.Table

@Dao
interface NoteDao {

    @Transaction
    @Query("Select * from notes Where foreignCategory == 0")
    fun getMain(): PagingSource<Int, FolderWithNote>

    @Transaction
    @Query("Select * from notes Where isDelete = 1")
    fun getBasket(): PagingSource<Int, FolderWithNote>

    @Transaction
    @Query("Select * from notes Where foreignCategory != 0")
    fun getOther()
    @Transaction
    @Query(" Select * from notes Where id = :id ")
    suspend fun getById(id: Int): FolderWithNote

    @Insert
    suspend fun insertNote(note: Table)

    @Delete
    suspend fun deleteNote(note: Table)

    @Update
    suspend fun updateNote(note: Table)

}