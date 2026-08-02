package com.example.v.ui.viewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.v.data.repository.FolderRepository
import com.example.v.data.model.Folder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoldersViewModel @Inject constructor(
    private val folderRepository: FolderRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow<String>("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val folders = _searchQuery.flatMapLatest {
        searchString -> folderRepository.getFolders(searchString)
    }.cachedIn(viewModelScope)

    fun update(folder:Folder){
        viewModelScope.launch{
            folderRepository.updateName(folder)
        }
    }
    fun insertFolder(folder: Folder){
       viewModelScope.launch{ folderRepository.insertFolder(folder) }
    }
    fun deleteFolder(folder: Folder){
        viewModelScope.launch{ folderRepository.deleteFolder(folder) }
    }
    fun searchFolder(stringSearch: String = ""){
        _searchQuery.value = stringSearch
    }
}