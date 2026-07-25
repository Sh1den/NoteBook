package com.example.v.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.v.data.model.FolderWithNote
import com.example.v.data.model.Table

@Dao
interface NoteDao {

    @Query("""
            Select * From notes
            Where foreignCategory = 2 And (title Like '%' || :searchString || '%')
            """
    )
    fun getBasket(searchString: String = ""): PagingSource<Int, FolderWithNote>

    @Query("""
            Select * From notes
            Where foreignCategory = 1 And (title  Like '%' || :searchString || '%')
            """
    )
    fun getMain(searchString: String = ""): PagingSource<Int, FolderWithNote>

    @Query("""
            Select * From notes
            Where (title Like '%' || :searchString || '%') And foreignCategory = :id
            """
    )
    fun getOther(searchString: String = "",id: Int): PagingSource<Int, FolderWithNote>

    @Query(" Select * from notes Where id = :id ")
    suspend fun getById(id: Int): FolderWithNote

    @Insert(
        //onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insertNote(note: Table)

    @Delete(
    )
    suspend fun deleteNote(note: Table)

    @Update
    suspend fun updateNote(note: Table)

}