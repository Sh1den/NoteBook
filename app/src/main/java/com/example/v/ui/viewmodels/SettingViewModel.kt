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

    val gridType = injector.countColumn

    fun getTheme(): ColorTheme {
        return ColorTheme.getTheme(injector.theme)
    }

    fun saveTheme(newColorTheme: ColorTheme) =  injector.getSharedManager().setTheme(newColorTheme.idTheme)

    fun setGridLayout(newGrid: GridColumn){
        injector.setGrid(newGrid)
    }
}