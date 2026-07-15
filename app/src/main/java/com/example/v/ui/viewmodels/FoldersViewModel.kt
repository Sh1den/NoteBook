package com.example.v.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.v.data.local.repository.FolderRepository
import com.example.v.data.model.Folder
import com.example.v.data.model.Folders
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoldersViewModel @Inject constructor(
    private val folderRepository: FolderRepository
): ViewModel() {
    val folders = folderRepository.getFolders().cachedIn(viewModelScope)

    fun insertFolder(folder: Folder){
       viewModelScope.launch(Dispatchers.IO) { folderRepository.insertFolder(folder) }
    }
    fun updateFolder(folder: Folder){
        viewModelScope.launch(Dispatchers.IO) { folderRepository.updateFolder(folder) }
    }
    fun deleteFolder(folder: Folder){
        viewModelScope.launch(Dispatchers.IO) { folderRepository.deleteFolder(folder) }
    }
}