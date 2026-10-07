package com.example.v.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.ScreenType
import com.example.v.data.repository.NoteRepository
import com.example.v.data.repository.SharedRepository
import com.example.v.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderNotesViewModel @Inject constructor(
    sharedRepository: SharedRepository,
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
): BaseNotesViewModel(sharedRepository,noteRepository) {

    val route = savedStateHandle.toRoute<Route.FolderNotes>()

    override val searchCategory: MutableStateFlow<ScreenType> = MutableStateFlow(ScreenType(Category.Others,route.id))

    @OptIn(ExperimentalCoroutinesApi::class)
    override val tableRepository = searchCategory.flatMapLatest { searchCategory ->
        noteRepository.getNotesByCategory(searchCategory)
    }.cachedIn(viewModelScope)

    fun colorChange(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
}