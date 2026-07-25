package com.example.v.ui.viewmodels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.v.data.local.repository.NoteRepository
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
): ViewModel(){
    private val route = savedStateHandle.toRoute<Route.NoteScreen>()
    val isChange = route.id != null
    private var _thNote = MutableStateFlow<Note>(Note(category = Category().apply {this.toCategory(route.stringCategory,route.foreignKey)}))
    val thNote = _thNote.asStateFlow()
    val thTime = DateTimeFormatter.ofPattern("d MMMM, H:mm", Locale.getDefault()).format(LocalDateTime.now())
    init {
        if (isChange) {
            viewModelScope.launch(Dispatchers.IO) {
                _thNote.value = noteRepository.getNoteById(route.id ?: 0,route.typeCategory)
            }
        }

    }

    fun saveNote(title: String,text: String){
        lateinit var newNote: Note
        newNote = _thNote.value.copy(title = title, text = text, time = thTime)
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
