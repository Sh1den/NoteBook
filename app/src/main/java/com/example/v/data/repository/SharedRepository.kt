package com.example.v.data.repository

import com.example.v.data.local.room.preference.SharedManager
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.data.model.SettingTopography
import com.example.v.data.model.TextStyleFontFamilyTheme
import com.example.v.data.model.TextStyleSpTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedRepository @Inject constructor(
    private val sharedManager: SharedManager
)  {
    private val _theme = MutableStateFlow(ColorTheme.getTheme(sharedManager.getTheme()))

    private val _countColumn = MutableStateFlow(GridColumn.getGridColumn(sharedManager.getGrid()))

    private val _settingTopography = MutableStateFlow(
        SettingTopography(
            textStyleSpTheme = TextStyleSpTheme.toTextSpTheme(sharedManager.getTopographySp()),
            textStyleFontFamilyTheme = TextStyleFontFamilyTheme.toTextStyleFontFamilyTheme(sharedManager.getTopographyFont())
        )
    )

    val theme = _theme.asStateFlow()
    val countColumn = _countColumn.asStateFlow()

    val settingTopography = _settingTopography.asStateFlow()

    fun setGrid(newGrid: GridColumn){
        _countColumn.value = newGrid
        sharedManager.setGrid(newGrid.countColumn)
    }

    fun saveTheme(newTheme: ColorTheme){
        _theme.value = newTheme
        sharedManager.setTheme(newTheme.idTheme)
    }

    fun setStyleSpTheme(newSpTheme: TextStyleSpTheme){
        _settingTopography.update { it.copy(textStyleSpTheme = newSpTheme) }
        sharedManager.setTopographySp(newSpTheme.value)
    }

    fun setStyleFontTheme(newFontTheme: TextStyleFontFamilyTheme){
        _settingTopography.update { it.copy(textStyleFontFamilyTheme = newFontTheme) }
        sharedManager.setTopographyFont(newFontTheme.value)
    }
}