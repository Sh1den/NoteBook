package com.example.v.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.v.data.local.room.mapper.toDomain
import com.example.v.data.local.room.mapper.toEntity
import com.example.v.data.local.room.dao.NoteDao
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.ScreenType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
){
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
    fun getNotesByCategory(searchCategory: ScreenType): Flow<PagingData<Note>> {
        return Pager(
            config = DEFAULT_PAGER_CONFIG,
            pagingSourceFactory = {
                when(searchCategory.name) {
                    Category.Main -> noteDao.getMain(searchCategory.getSearchString())
                    Category.Basket -> noteDao.getBasket(searchCategory.getSearchString())
                    Category.Others -> noteDao.getOther(searchCategory.idFolder ?: 0,searchCategory.getSearchString())
                }
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }
    suspend fun getNoteById(id: Int): Note{
        return noteDao.getById(id).toDomain()
    }

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note.toEntity())
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note.toEntity())
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note.toEntity())
    }
}

