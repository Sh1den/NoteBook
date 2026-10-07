package com.example.v.ui.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.ScreenType
import com.example.v.data.repository.NoteRepository
import com.example.v.data.repository.SharedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    sharedRepository: SharedRepository,
    private val noteRepository: NoteRepository
): BaseNotesViewModel(sharedRepository,noteRepository) {

    override val searchCategory: MutableStateFlow<ScreenType> = MutableStateFlow(ScreenType(Category.Basket))

    @OptIn(ExperimentalCoroutinesApi::class)
    override val tableRepository = searchCategory.flatMapLatest { searchCategory ->
        noteRepository.getNotesByCategory(searchCategory)
    }.cachedIn(viewModelScope)
    fun restoreToBasket(note: Note){
        viewModelScope.launch{
            noteRepository.updateNote(note.copy(isBasket = false))
        }
    }

    fun deleteNotes(note: Note) = viewModelScope.launch{ noteRepository.deleteNote(note) }
}