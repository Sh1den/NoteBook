package com.example.v.ui.viewmodels
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.v.data.repository.NoteRepository
import com.example.v.data.local.room.preference.SharedManager
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.ScreenType
import com.example.v.data.repository.SharedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    sharedRepository: SharedRepository,
    savedStateHandle: SavedStateHandle
): BaseNotesViewModel(sharedRepository,noteRepository)  {

    override val searchCategory: MutableStateFlow<ScreenType> = MutableStateFlow(ScreenType())

    @OptIn(ExperimentalCoroutinesApi::class)
    override val tableRepository = searchCategory.flatMapLatest { searchCategory ->
        noteRepository.getNotesByCategory(searchCategory)
    }.cachedIn(viewModelScope)

    fun colorChange(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
}