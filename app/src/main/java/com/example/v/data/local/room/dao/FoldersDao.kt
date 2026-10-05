package com.example.v.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.v.data.local.room.entity.Folders
import com.example.v.data.local.room.relation.CountNotesWithFolders

@Dao
interface FoldersDao{
    @Transaction
    @Query("""
        Select Folders.id,folderName,Count(notes.id) as countNotes  from Folders
        Left Join notes On Folders.id = notes.foreignCategory And notes.isDelete = 0
        Where (Folders.id != 0  And folderName Like '%' || :stringSearch || '%')
        Group by Folders.id
        """)
    fun getFolders(stringSearch: String = ""): PagingSource<Int, CountNotesWithFolders>

    @Query("UPDATE Folders Set folderName = :newCategoryName Where id = :oldId")
    suspend fun updateFolderName(oldId: Int,newCategoryName: String)
    @Insert
    suspend fun insertFolders(folder: Folders)
    @Delete
    suspend fun  deleteFolders(folders: Folders)
}