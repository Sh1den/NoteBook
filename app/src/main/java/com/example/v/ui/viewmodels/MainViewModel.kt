package com.example.v.ui.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.v.data.local.repository.NoteRepository
import com.example.v.data.local.shared_preferences.SharedManager
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.SearchCategory
import com.example.v.data.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val sharedManager: SharedManager,
    private val noteRepository: NoteRepository
): ViewModel()  {

    private val _searchCategory = MutableStateFlow<SearchCategory>(
        SearchCategory(Category())
    )
    val searchCategory = _searchCategory.value

    val tableRepository = _searchCategory.flatMapLatest {
        searchCategory -> noteRepository.getNotesByCategory(searchCategory)
    }.cachedIn(viewModelScope)
    private var _theme = MutableStateFlow<Theme>(Theme(sharedManager.getTheme()))
    val theme: StateFlow<Theme> = _theme

    fun setTheme(thTheme: String,typeTheme: Theme.ColorTheme){
        _theme.update { it.copy(thTheme,typeTheme) }
        sharedManager.setTheme(Theme(thTheme,typeTheme))
    }

    fun deleteNotes(note: Note) = viewModelScope.launch{ noteRepository.deleteNote(note) }

    fun toBasket(note:Note) = viewModelScope.launch(Dispatchers.IO) { noteRepository.toBasket(note)}
    fun restoreToBasket(note: Note) = viewModelScope.launch(Dispatchers.IO){ noteRepository.restoreToBasket(note) }

    fun colorChange(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
    fun SearchNote(title: String = ""){
        _searchCategory.value = _searchCategory.value.copy().apply { this.toSearch(title) }
    }
    fun setCategory(newCategory: Category){
        _searchCategory.value = SearchCategory(newCategory)
    }

}