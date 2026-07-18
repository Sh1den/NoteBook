package com.example.v.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.v.data.local.mapper.toEntity
import com.example.v.data.local.repository.NoteRepository
import com.example.v.data.local.room.NoteDao
import com.example.v.data.local.shared_preferences.SharedManager
import com.example.v.data.model.Category
import com.example.v.data.model.Note
import com.example.v.data.model.Table
import com.example.v.data.model.Theme
import com.example.v.data.model.TypeCategory
import com.example.v.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val sharedManager: SharedManager,
    private val noteRepository: NoteRepository
): ViewModel()  {

    private val _category = MutableStateFlow<Category>(
        Category()
    )
    val category = _category.value

    val tableRepository = _category.flatMapLatest {
        category -> noteRepository.getNotesByCategory(category)
    }.cachedIn(viewModelScope)
    private var _theme = MutableStateFlow<Theme>(Theme(sharedManager.getTheme()))
    val theme: StateFlow<Theme> = _theme

    fun setTheme(thTheme: String,typeTheme: Theme.ColorTheme){
        _theme.update { it.copy(thTheme,typeTheme) }
        sharedManager.setTheme(Theme(thTheme,typeTheme))
    }

    fun deleteNotes(note: Note) = viewModelScope.launch(Dispatchers.IO) { noteRepository.deleteNote(note) }

    fun toBasket(note:Note){

    }

    fun SearchNote(title: String){
        _category.value = _category.value.copy().apply {this.toSearch(title)}
    }
    fun setCategory(newCategory: Category){
        _category.value = newCategory
    }

}