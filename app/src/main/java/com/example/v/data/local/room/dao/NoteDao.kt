package com.example.v.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.v.data.local.room.relation.FolderWithNote
import com.example.v.data.local.room.entity.Table

@Dao
interface NoteDao {

    @Transaction
    @Query("""
        Select * from notes 
        Where foreignCategory is Null And isDelete = 0 And title Like '%' || :stringSearch || '%'
     """)
    fun getMain(stringSearch: String = ""): PagingSource<Int, FolderWithNote>

    @Transaction
    @Query("""
        Select * from notes
        Where isDelete = 1 And title Like '%' || :stringSearch || '%'
        """)
    fun getBasket(stringSearch: String = ""): PagingSource<Int, FolderWithNote>

    @Transaction
    @Query("""
        Select * from notes 
        Where foreignCategory = :foreignCategory And isDelete = 0 And title Like '%' || :stringSearch || '%'
        """)
    fun getOther(foreignCategory: Int ,stringSearch: String = ""): PagingSource<Int, FolderWithNote>

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