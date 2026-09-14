package com.example.v.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.v.data.local.room.entity.Folders

@Dao
interface FoldersDao{

    @Query("Select * from Folders Where id != 0 And folderName Like '%' || :stringSearch || '%'")
    fun getFolders(stringSearch: String = ""): PagingSource<Int, Folders>


    @Query("UPDATE Folders Set folderName = :newCategoryName Where id = :oldId")
    suspend fun updateFolderName(oldId: Int,newCategoryName: String)
    @Insert
    suspend fun insertFolders(folder: Folders)
    @Delete
    suspend fun  deleteFolders(folders: Folders)
    @Update
    suspend fun  updateFolders(folders: Folders)
}