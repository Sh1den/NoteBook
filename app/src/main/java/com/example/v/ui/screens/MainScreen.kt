package com.example.v.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.v.R
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.CastFloatingActionButton
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.NoteCard
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    onClick: () -> Unit
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            NavigationTopAppBar(
                stringResource(R.string.app_name), NavigationItems.Menu,mutableListOf(NavigationItems.Search,
                NavigationItems.More),null, onNavClick = onClick)
        },
        floatingActionButton = {
            CastFloatingActionButton(Modifier.size(60.dp)){
                navController.navigate(Route.NoteScreen)
            }
        }
    ) {
        val notesPagging = mainViewModel.tableInfo.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(
                count = notesPagging.itemCount,
                key = notesPagging.itemKey { Note ->  Note.id}
            ){
                val note = notesPagging[it]
                NoteCard(note)
            }
        }
    }
}