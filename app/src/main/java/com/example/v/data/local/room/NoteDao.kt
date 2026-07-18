package com.example.v.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.v.data.model.Note
import com.example.v.data.model.Table
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("""
            Select * From notes
            Where category = 'Basket' And (name_notes Like '%' || :searchString || '%') And isSystem
            """
    )
    fun getBasket(searchString: String = ""): PagingSource<Int, Table>

    @Query("""
            Select * From notes
            Where category = 'Main' And (name_notes Like '%' || :searchString || '%') And isSystem
            """
    )
    fun getMain(searchString: String = ""): PagingSource<Int, Table>

    @Query("""
            Select * From notes
            Where category = :category And (name_notes Like '%' || :searchString || '%') And Not(isSystem)
            """
    )
    fun getOther(category:String,searchString: String = ""): PagingSource<Int, Table>

    @Query(" Select * from notes Where id = :id ")
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