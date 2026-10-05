package com.example.v.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.v.data.local.room.preference.SharedManager
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.data.model.TextStyleFontFamilyTheme
import com.example.v.data.model.TextStyleSpTheme
import com.example.v.data.repository.SharedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val sharedRepository: SharedRepository
): ViewModel(){

    val gridType = sharedRepository.countColumn

    val theme = sharedRepository.theme

    val topography = sharedRepository.settingTopography

    fun saveTheme(newColorTheme: ColorTheme) = sharedRepository.saveTheme(newColorTheme)

    fun setGridLayout(newGrid: GridColumn) = sharedRepository.setGrid(newGrid)

    fun setTopographySp(newSp: TextStyleSpTheme) = sharedRepository.setStyleSpTheme(newSp)

    fun setTopographyFont(newFont: TextStyleFontFamilyTheme) = sharedRepository.setStyleFontTheme(newFont)
}