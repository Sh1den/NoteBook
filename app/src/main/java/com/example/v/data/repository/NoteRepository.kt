package com.example.v.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.v.data.local.mapper.toDomain
import com.example.v.data.local.mapper.toEntity
import com.example.v.data.local.dao.NoteDao
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.SearchCategory
import com.example.v.data.model.TypeCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


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
    fun getNotesByCategory(searchCategory: SearchCategory): Flow<PagingData<Note>> {
        return Pager(
            config = DEFAULT_PAGER_CONFIG,
            pagingSourceFactory = {
                when(searchCategory.category.typeCategory){
                    TypeCategory.BASKET -> noteDao.getBasket(searchString = searchCategory.getSearchString().parseLike())
                    TypeCategory.MAIN -> noteDao.getMain(searchString = searchCategory.getSearchString().parseLike())
                    TypeCategory.OTHER -> noteDao.getOther(searchString = searchCategory.getSearchString().parseLike(),searchCategory.category.categoryId)
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
    suspend fun toBasket(
        note: Note
    ) {
        val newNote = note.copy(category = Category.getBasketCategory())
        noteDao.updateNote(newNote.toEntity())
    }

    suspend fun restoreToBasket(note: Note){
        val newNote = note.copy(category = note.category.copy(categoryId = note.previousForeignCategory), previousForeignCategory = 0)
        noteDao.updateNote(newNote.toEntity())
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

