package com.example.v.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.v.data.local.room.mapper.toDomain
import com.example.v.data.local.room.mapper.toEntity
import com.example.v.data.local.room.dao.FoldersDao
import com.example.v.data.model.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FolderRepository @Inject constructor(
    private val foldersDao: FoldersDao
) {
    companion object{
       private val DEFAULT_PAGER_CONFIG = PagingConfig(
        pageSize = 20,
        prefetchDistance = 5,
        enablePlaceholders = false
        )
        private fun String.parseLike(): String{
            return this
                .replace("\\","\\\\")
                .replace("%","\\%")
                .replace("_","\\_")
        }
    }
    fun getFolders(searchFolder: String = ""): Flow<PagingData<Folder>>{
        return Pager(
            config = DEFAULT_PAGER_CONFIG,
            pagingSourceFactory = {foldersDao.getFolders(searchFolder.parseLike())}
        ).flow.map {
            pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }

    suspend fun updateName(folder: Folder){
        Log.d("SDDSADAD",folder.toString())
        foldersDao.updateFolderName(folder.id,folder.name)
    }
    suspend fun insertFolder(folder: Folder){
        foldersDao.insertFolders(folder.toEntity())
    }
    suspend fun deleteFolder(folder: Folder){
        foldersDao.deleteFolders(folder.toEntity())
    }
}