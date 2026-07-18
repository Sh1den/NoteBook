package com.example.v.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.v.data.local.mapper.toDomain
import com.example.v.data.local.mapper.toEntity
import com.example.v.data.local.room.FoldersDao
import com.example.v.data.model.Folder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FolderRepository(
    private val foldersDao: FoldersDao
) {
    fun getFolders(searchFolder: String = ""): Flow<PagingData<Folder>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {foldersDao.getFolders(searchFolder)}
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }
    suspend fun insertFolder(folder: Folder){
        foldersDao.insertFolders(folder.toEntity())
    }
    suspend fun updateFolder(folder: Folder){
        foldersDao.updateFolders(folder.toEntity())
    }
    suspend fun deleteFolder(folder: Folder){
        foldersDao.deleteFolders(folder.toEntity())
    }
}