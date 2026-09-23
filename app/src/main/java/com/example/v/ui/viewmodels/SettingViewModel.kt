package com.example.v.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.v.data.local.room.preference.SharedManager
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val injector: Injector
): ViewModel(){

    private val _theme = MutableStateFlow(ColorTheme.getTheme(injector.getSharedManager().getTheme()))
    val theme: StateFlow<ColorTheme> = _theme

    val gridType = injector.countColumn

    fun setTheme(newColorTheme: ColorTheme){
        injector.getSharedManager().setTheme(newColorTheme.idTheme)
        _theme.update { newColorTheme }
    }

    fun setGridLayout(newGrid: GridColumn){
        injector.setGrid(newGrid)
    }
}