package com.example.v.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.v.R
import com.example.v.data.model.Category
import com.example.v.ui.navigation.NavigationItems
import com.example.v.data.model.Note
import com.example.v.ui.components.CastFloatingActionButton
import com.example.v.ui.components.GetNotes
import com.example.v.ui.components.ModalBottomColors
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.MainViewModel
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    onClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        mainViewModel.setCategory(Category.getMainCategory())
    }
    var searchString by remember { mutableStateOf("") }
    val selectedNote = remember { mutableStateListOf<Note>()}
    var isSearch by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val bottomIsOpen = remember { mutableStateOf(false) }
    LaunchedEffect(isSearch) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (selectedNote.isNotEmpty()){
                val actionIcons = mutableListOf<NavigationItems>(NavigationItems.Basket)
                val onActionClicks = mutableListOf({
                    selectedNote.forEach { mainViewModel.toBasket(it) }
                    selectedNote.clear()
                })
                if (selectedNote.size == 1){
                    actionIcons.add(0, NavigationItems.Palette)
                    onActionClicks.add(0) {
                        bottomIsOpen.value = true
                    }
                }
                NavigationTopAppBar(
                    navIcons = NavigationItems.Back,
                    actionIcons = actionIcons,
                    onActionsClicksIcons = onActionClicks,
                    onNavClick = {selectedNote.clear()}
                )
            }
            else{
                if (!isSearch) {
                    NavigationTopAppBar(
                        titleBar = stringResource(R.string.app_name),
                        navIcons = NavigationItems.Menu,
                        actionIcons = mutableListOf(
                            NavigationItems.Search
                        ),
                        onActionsClicksIcons = mutableListOf({ isSearch = true }),
                        onNavClick = onClick
                    )
                }
                else{
                    NavigationTopAppBar(
                        titleWidget = {
                            OutlinedTextField(
                                value = searchString,
                                onValueChange = {
                                    searchString = it
                                    mainViewModel.searchNote(searchString)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequester),
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
                            mainViewModel.searchNote()
                            isSearch = false
                            searchString = ""

                        }
                    )
                }
            }
        },
        floatingActionButton = {
            CastFloatingActionButton(Modifier.size(60.dp), Modifier.size(30.dp),RoundedCornerShape(15.dp)) {
                navController.navigate(
                    Route.NoteScreen()
                )
            }
        }
    ) {
        GetNotes(mainViewModel,navController,it,selectedNote)
        if (bottomIsOpen.value){
            ModalBottomColors(bottomIsOpen,mainViewModel,selectedNote,bottomIsOpen)
        }
    }
}
