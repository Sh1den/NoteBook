package com.example.v.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.v.data.model.Category
import com.example.v.data.model.GridColumn
import com.example.v.data.model.Note
import com.example.v.data.model.ScreenType
import com.example.v.data.repository.NoteRepository
import com.example.v.data.repository.SharedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

abstract class BaseNotesViewModel(
    sharedRepository: SharedRepository,
    private val noteRepository: NoteRepository
): ViewModel() {
    abstract val searchCategory: MutableStateFlow<ScreenType>

    abstract val tableRepository: Flow<PagingData<Note>>
   val gridType: StateFlow<GridColumn> = sharedRepository.countColumn

    fun toBasket(note:Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note.copy(isBasket = true))
        }
    }

    fun searchNote(title: String = ""){
        searchCategory.value = searchCategory.value.copy().apply { this.toSearch(title) }
    }

    fun isEditNotes(): Boolean {
        return searchCategory.value.name != Category.Basket
    }
}