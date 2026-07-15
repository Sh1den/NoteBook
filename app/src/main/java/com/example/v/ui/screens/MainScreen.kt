package com.example.v.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import androidx.paging.filter
import com.example.v.R
import com.example.v.data.model.Category
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.Note
import com.example.v.data.model.Table
import com.example.v.data.model.TypeCategory
import com.example.v.ui.components.CastFloatingActionButton
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.NoteCard
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.map
import kotlin.math.acos

@Composable
fun MainScreen(
    navController: NavController,
    category: Category,
    onClick: () -> Unit
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    mainViewModel.setCategory(category)
    var selectedNote = remember { mutableStateListOf<Note>()}
    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            if(selectedNote.size == 0) {
                GetCategoryAppBar(category,false,onClick,{})
            }
            else{
                GetCategoryAppBar(category, true, { selectedNote.clear()}, {
                    selectedNote.forEach {
                        mainViewModel.deleteNotes(it)
                    }
                    selectedNote.clear()
                })
            }
        },
        floatingActionButton = {
            if(category.typeCategory != TypeCategory.BASKET) {
                CastFloatingActionButton(Modifier.size(60.dp)) {
                    navController.navigate(Route.NoteScreen(stringCategory = category.stringCategory, typeCategory = category.typeCategory))
                }
            }
        }
    ) {
        val notesPagging = mainViewModel.tableRepository.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = 7.dp)
        ) {
            items(
                count = notesPagging.itemCount,
                key = notesPagging.itemKey { Note ->  Note.id}
            ){
                val note = notesPagging[it]
                note?.let {
                    NoteCard(note,navController,{ selectedNote.contains(it) },{ selectedNote.add(it)}) {
                        if (selectedNote.size != 0) selectedNote.add(it)
                        else {
                            navController.navigate(Route.NoteScreen(it.id,it.category.stringCategory))
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun GetTitle(category: Category): String{
    return when(category.typeCategory){
        TypeCategory.MAIN -> stringResource(R.string.app_name)
        TypeCategory.BASKET -> stringResource(R.string.basket_app)
        else -> category.stringCategory
    }
}
@Composable
private fun GetSelectAppBarCategory(category: Category,onNavClick: () -> Unit,onActionsClicks: () -> Unit){
    when (category.typeCategory) {
        TypeCategory.BASKET -> {
            NavigationTopAppBar(
                navIcons = NavigationItems.Back,
                actiomText = stringResource(R.string.delete),
                onActionsClicks = mutableListOf(onActionsClicks),
                onNavClick = onNavClick
            )
        }
        else -> {
            NavigationTopAppBar(
                navIcons = NavigationItems.Back,
                actionIcons = mutableListOf(NavigationItems.Basket),
                onActionsClicks = mutableListOf(onActionsClicks),
                onNavClick = onNavClick
            )
        }
    }
}

@Composable
private fun GetUnselectAppBarCategory(
    category: Category,
    onNavClick: () -> Unit,
    onActionsClicks: () -> Unit
) {
    NavigationTopAppBar(
        titleBar = GetTitle(category),
        navIcons = NavigationItems.Menu,
        actionIcons = mutableListOf(
            NavigationItems.Search
        ),
        onActionsClicks = mutableListOf(onActionsClicks),
        onNavClick = onNavClick
    )
}


@Composable
private fun GetCategoryAppBar(category: Category,isSelected: Boolean,onNavClick: () -> Unit,onActionsClicks: () -> Unit){
    if (isSelected) {
        GetSelectAppBarCategory(category,onNavClick,onActionsClicks)
    }
    else{
        GetUnselectAppBarCategory(category,onNavClick,onActionsClicks)
    }
}
