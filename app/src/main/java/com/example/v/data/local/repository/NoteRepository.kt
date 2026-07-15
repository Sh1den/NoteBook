package com.example.v.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.v.data.local.mapper.toDomain
import com.example.v.data.local.mapper.toEntity
import com.example.v.data.local.room.FoldersDao
import com.example.v.data.local.room.NoteDao
import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.data.model.Note
import com.example.v.data.model.Table
import com.example.v.data.model.TypeCategory
import com.example.v.ui.navigation.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class NoteRepository(
    private val noteDao: NoteDao
){
    fun getNotesByCategory(category: Category): Flow<PagingData<Note>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                when(category.typeCategory){
                    TypeCategory.BASKET -> noteDao.getBasket()
                    TypeCategory.MAIN -> noteDao.getMain()
                    TypeCategory.OTHER -> noteDao.getOther(category.stringCategory)
                }
            }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
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

