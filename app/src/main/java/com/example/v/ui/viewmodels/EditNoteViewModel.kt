package com.example.v.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.v.data.local.repository.NoteRepository
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.TypeCategory
import com.example.v.ui.navigation.Route
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Time
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
): ViewModel(){
    private val route = savedStateHandle.toRoute<Route.NoteScreen>()
    val isChange = route.id != null
    private var _thNote = MutableStateFlow<Note>(Note(category = Category(route.typeCategory,route.stringCategory)))
    val thNote = _thNote.asStateFlow()
    val thTime = DateTimeFormatter.ofPattern("d MMMM, H:mm", Locale.getDefault()).format(LocalDateTime.now())
    init {
        if (isChange) {
            viewModelScope.launch(Dispatchers.IO) {
                _thNote.value = noteRepository.getNoteById(route.id ?: 0)
            }
        }

    }

    fun saveNote(title: String,text: String){
        val newNote = _thNote.value.copy(title = title, text = text, time = thTime)
        if (isChange) update(newNote)
        else insert(newNote)
    }
    fun update(note:Note) {
        viewModelScope.launch(Dispatchers.IO) { noteRepository.updateNote(note) }
    }
    fun insert(note:Note) {
        viewModelScope.launch(Dispatchers.IO) { noteRepository.insertNote(note) }
    }
}
