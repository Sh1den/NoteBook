package com.example.v.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel()  {
    private var _notes = MutableStateFlow<MutableList<String?>>(mutableListOf())
    val notes: StateFlow<MutableList<String?>> = _notes


    fun AddNote(note: String){
        _notes.value.add(note)
    }
}