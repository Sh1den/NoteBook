package com.example.v.ui.screens
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.v.R
import com.example.v.data.model.Folder
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.CustomDialog
import com.example.v.ui.components.GetFolders
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.viewmodels.FoldersViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FolderScreen(
    navController: NavController,
    onClick: () -> Unit
) {
    var showDialog = remember { mutableStateOf(false) }
    val foldersViewModel: FoldersViewModel = hiltViewModel()
    var selectedFolder = remember { mutableStateListOf<Folder>()}
    val focusRequester = remember { FocusRequester() }
    val currentUpdateFolder = remember { mutableStateOf(Folder()) }
    var isRename = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            if (selectedFolder.size == 0) {
                var isSearch by remember { mutableStateOf(false) }
                if (!isSearch) {
                    NavigationTopAppBar(
                        {},
                        stringResource(R.string.archives_of_notes),
                        NavigationItems.Menu,
                        mutableListOf(
                            NavigationItems.NewFolder,
                            NavigationItems.Search
                        ),
                        null,
                        listOf({ showDialog.value = true }, {isSearch = true}),
                        onNavClick = onClick
                    )
                }
                else{
                    var searchString by remember { mutableStateOf("") }
                    NavigationTopAppBar(
                        titleWidget = {
                            LaunchedEffect(Unit) {
                                focusRequester.requestFocus()
                                keyboardController?.show()
                            }
                            OutlinedTextField(
                                value = searchString,
                                onValueChange = {
                                    searchString = it
                                    foldersViewModel.searchFolder(searchString)
                                },
                                modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent
                                ),
                            )
                        },
                        navIcons = NavigationItems.Back,
                        onNavClick = {
                            foldersViewModel.searchFolder()
                            isSearch = false
                        }
                    )
                }
            }
            else {
                if (isRename.value) {
                    NavigationTopAppBar(
                        navIcons = NavigationItems.Cancel,
                        actionIcons = mutableListOf(NavigationItems.Ok),
                        onActionsClicks = mutableListOf({
                            foldersViewModel.update(currentUpdateFolder.value)
                            selectedFolder.clear()
                            isRename.value = false
                        })
                    ){
                        isRename.value = false
                        selectedFolder.clear()
                    }
                }
                else {
                    val actionText = mutableListOf(stringResource(R.string.delete))
                    val onActionClicks = mutableListOf(
                        {
                            selectedFolder.forEach { item ->
                                foldersViewModel.deleteFolder(item)
                            }
                            selectedFolder.clear()
                        }
                    )
                    if (selectedFolder.size == 1) {
                        actionText.add(0,stringResource(R.string.rename))
                        onActionClicks.add(0,{ isRename.value = true })
                    }
                    NavigationTopAppBar(
                        navIcons = NavigationItems.Back,
                        actionText = actionText,
                        onActionsClicks = onActionClicks,
                        onNavClick = { selectedFolder.clear() }
                    )
                }
            }
        }
    ) {
        if (showDialog.value) {
            var nameNewCategory = remember { mutableStateOf("") }
            CustomDialog(showDialog,nameNewCategory,it,foldersViewModel)
        }
        GetFolders(foldersViewModel,navController,it,selectedFolder, isRename,currentUpdateFolder)
    }
}