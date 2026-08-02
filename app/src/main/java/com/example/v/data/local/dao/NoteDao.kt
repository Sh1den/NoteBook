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
    @Query("""
            Select * From notes
            Where foreignCategory = (Select id FROM Folders WHERE typeCategory = 'BASKET' LIMIT 1) 
            And ((title Like :searchString || '%' And title != '') Or (text Like :searchString || '%' And title = ''))
            """
    )
    fun getBasket(searchString: String = ""): PagingSource<Int, FolderWithNote>

    @Transaction
    @Query("""
            Select * From notes
            Where foreignCategory = (Select id FROM Folders WHERE typeCategory = 'MAIN' LIMIT 1)
            And ((title Like :searchString || '%' And title != '') Or (text Like :searchString || '%' And title = ''))
            """
    )
    fun getMain(searchString: String = ""): PagingSource<Int, FolderWithNote>

    @Transaction
    @Query("""
            Select * From notes
            Where ((title Like :searchString || '%' And title != '') Or (text Like :searchString || '%' And title = ''))  And foreignCategory = :id
            """
    )
    fun getOther(searchString: String = "",id: Int): PagingSource<Int, FolderWithNote>
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