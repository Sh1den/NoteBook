package com.example.v.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.v.data.local.room.NoteDao
import com.example.v.data.local.shared_preferences.SharedManager
import com.example.v.data.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val sharedManager: SharedManager,
    val NoteDao: NoteDao
): ViewModel()  {

    val tableInfo = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {NoteDao.getInfo()}
    ).flow.cachedIn(viewModelScope)
    private var _theme = MutableStateFlow<Theme>(Theme(sharedManager.getTheme()))
    val theme: StateFlow<Theme> = _theme

    fun setTheme(thTheme: String,typeTheme: Theme.ColorTheme){
        _theme.update { it.copy(thTheme,typeTheme) }
        sharedManager.setTheme(Theme(thTheme,typeTheme))
    }

}