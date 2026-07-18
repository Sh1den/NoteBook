package com.example.v.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.v.data.model.Category
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.Note
import com.example.v.ui.components.GetNotes
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.viewmodels.MainViewModel

@Composable
fun BasketScreen(
    navController: NavController,
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
                    actionText = mutableListOf(stringResource(R.string.delete)),
                    onActionsClicks = mutableListOf({

                    }, {
                        selectedNote.forEach {
                            mainViewModel.deleteNotes(it)
                        }
                        selectedNote.clear()
                    }),
                    onNavClick = {selectedNote.clear()}
                )
            }
            else{
                if (!isSearch) {
                    NavigationTopAppBar(
                        titleBar = stringResource(R.string.basket_app),
                        navIcons = NavigationItems.Back,
                        actionIcons = mutableListOf(
                            NavigationItems.Search
                        ),
                        onActionsClicks = mutableListOf({isSearch = true}),
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
                                    mainViewModel.SearchNote(searchString)
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
        }
    ) {
        GetNotes(mainViewModel,navController,it,selectedNote)
    }
}