package com.example.v.ui.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.v.data.repository.NoteRepository
import com.example.v.data.local.preference.SharedManager
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.SearchCategory
import com.example.v.data.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedManager: SharedManager,
    private val noteRepository: NoteRepository
): ViewModel()  {

    private val _searchCategory = MutableStateFlow(
        SearchCategory(Category())
    )
    @OptIn(ExperimentalCoroutinesApi::class)
    val tableRepository = _searchCategory.flatMapLatest {
        searchCategory -> noteRepository.getNotesByCategory(searchCategory)
    }.cachedIn(viewModelScope)
    private val _theme = MutableStateFlow(Theme(sharedManager.getTheme()))
    val theme: StateFlow<Theme> = _theme

    fun setTheme(thTheme: String,typeTheme: Theme.ColorTheme){
        _theme.update { it.copy(strTheme = thTheme, colorTheme = typeTheme) }
        sharedManager.setTheme(Theme(thTheme,typeTheme))
    }

    fun deleteNotes(note: Note) = viewModelScope.launch{ noteRepository.deleteNote(note) }

    fun toBasket(note:Note) = viewModelScope.launch{ noteRepository.toBasket(note)}
    fun restoreToBasket(note: Note) = viewModelScope.launch{ noteRepository.restoreToBasket(note) }

    fun colorChange(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
    fun searchNote(title: String = ""){
        _searchCategory.value = _searchCategory.value.copy().apply { this.toSearch(title) }
    }
    fun setCategory(newCategory: Category){
        _searchCategory.value = SearchCategory(newCategory)
    }

}