package com.example.v.ui.viewmodels

import com.example.v.data.local.room.preference.SharedManager
import com.example.v.data.model.GridColumn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Injector @Inject constructor(
   private val sharedManager: SharedManager
) {
    private val _countColumn = MutableStateFlow(GridColumn.getGridColumn(sharedManager.getGrid()))
    var countColumn = _countColumn.asStateFlow()

    fun setGrid(newGrid: GridColumn){
        _countColumn.value = newGrid
        sharedManager.setGrid(newGrid.countColumn)
    }

    fun getSharedManager() = sharedManager
}