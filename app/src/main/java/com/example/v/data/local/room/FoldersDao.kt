package com.example.v.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.v.data.model.Folders
import com.example.v.data.model.Table

@Dao
interface FoldersDao{
    @Query("Select * from Folders Where isSystem = false And category_id Like '%' || :stringSearch || '%'")
    fun getFolders(stringSearch: String = ""): PagingSource<Int, Folders>
    @Insert(
        onConflict = OnConflictStrategy.ABORT
    )
    suspend fun insertFolders(folder: Folders)
    @Delete
    suspend fun  deleteFolders(folders: Folders)
    @Update
    suspend fun  updateFolders(folders: Folders)
}