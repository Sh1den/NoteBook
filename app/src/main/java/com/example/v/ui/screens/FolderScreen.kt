package com.example.v.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.v.R
import com.example.v.data.model.Folder
import com.example.v.data.model.Folders
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.Note
import com.example.v.ui.components.CustomDialog
import com.example.v.ui.components.FolderCard
import com.example.v.ui.components.GetFolders
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.NoteCard
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.FoldersViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FolderScreen(
    navController: NavController,
    onClick: () -> Unit
) {
    var showDialog = remember { mutableStateOf(false) }
    val foldersViewModel: FoldersViewModel = hiltViewModel()
    var searchString by remember { mutableStateOf("") }
    var selectedFolder = remember { mutableStateListOf<Folder>()}
    var isSearch by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            if (selectedFolder.size == 0) {
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
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent
                                ),
                            )
                        },
                        navIcons = NavigationItems.Back,
                        onNavClick = {
                            isSearch = false
                        }
                    )
                }
            }
            else{
                NavigationTopAppBar(
                    navIcons = NavigationItems.Back,
                    actionIcons = mutableListOf(NavigationItems.Basket),
                    onActionsClicks = mutableListOf(
                        {
                            selectedFolder.forEach { item ->
                                foldersViewModel.deleteFolder(item)
                            }
                            selectedFolder.clear()
                        }
                    ),
                    onNavClick = { selectedFolder.clear()}
                )
            }
        }
    ) {
        if (showDialog.value) {
            var nameNewCategory = remember { mutableStateOf("") }
            CustomDialog(showDialog,nameNewCategory,it,foldersViewModel)
        }
        GetFolders(foldersViewModel,navController,it,selectedFolder)
    }
}