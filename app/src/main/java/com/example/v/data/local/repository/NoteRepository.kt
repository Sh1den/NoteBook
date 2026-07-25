package com.example.v.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.v.data.local.mapper.toDomain
import com.example.v.data.local.mapper.toEntity
import com.example.v.data.local.room.NoteDao
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.SearchCategory
import com.example.v.data.model.TypeCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class NoteRepository(
    private val noteDao: NoteDao
){
    fun getNotesByCategory(searchCategory: SearchCategory): Flow<PagingData<Note>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                when(searchCategory.category.typeCategory){
                    TypeCategory.BASKET -> noteDao.getBasket(searchString = searchCategory.getSearchString())
                    TypeCategory.MAIN -> noteDao.getMain(searchString = searchCategory.getSearchString())
                    TypeCategory.OTHER -> noteDao.getOther(searchString = searchCategory.getSearchString(),searchCategory.category.categoryId)
                }
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }
    }
    suspend fun getNoteById(id: Int,typeCategory: TypeCategory): Note{
        return noteDao.getById(id).toDomain()
    }
    suspend fun toBasket(
        note: Note
    ) {
        val newNote = note.copy(previousForeignCategory = note.category.categoryId, category = Category().apply {this.toBasket()})
        noteDao.updateNote(newNote.toEntity())
    }

    suspend fun restoreToBasket(note: Note){
        val newNote = note.copy(category = note.category.copy(categoryId = note.previousForeignCategory ?: 0), previousForeignCategory = null)
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

