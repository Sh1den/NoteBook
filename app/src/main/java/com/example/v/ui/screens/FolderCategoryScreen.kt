package com.example.v.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.v.R
import com.example.v.data.model.Category
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.Note
import com.example.v.ui.components.CastFloatingActionButton
import com.example.v.ui.components.GetNotes
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.MainViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderCategoryScreen(
    navController: NavController,
    category: Category,
    mainViewModel: MainViewModel,
    onClick: () -> Unit
){
    var selectedNote = remember { mutableStateListOf<Note>()}
    var searchString by remember { mutableStateOf("") }
    var isSearch by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            if (selectedNote.size != 0) {
                NavigationTopAppBar(
                    navIcons = NavigationItems.Back,
                    actionIcons = mutableListOf(NavigationItems.Basket),
                    onActionsClicks = mutableListOf({
                        selectedNote.forEach { mainViewModel.toBasket(it) }
                        selectedNote.clear()
                    }),
                    onNavClick = {selectedNote.clear()}
                )
            }
            else{
                if(!isSearch){
                NavigationTopAppBar(
                    titleBar = category.stringCategory,
                    navIcons = NavigationItems.Back,
                    actionIcons = mutableListOf(
                        NavigationItems.Search
                    ),
                    onActionsClicks = mutableListOf({
                        isSearch = true
                    }),
                    onNavClick = onClick
                )}
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
                                    mainViewModel.SearchNote(searchString)
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
                            searchString = ""
                            isSearch = false
                        }
                    )
                }
            }

        },
        floatingActionButton = {
            CastFloatingActionButton(Modifier.size(60.dp)) {
                navController.navigate(
                    Route.NoteScreen(stringCategory = category.stringCategory, typeCategory = category.typeCategory)
                )
            }
        }
    ) {
        GetNotes(mainViewModel,navController,it,selectedNote)
    }
}