package com.example.v.ui.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.v.LocalSharedStateLanguage
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.CastFloatingActionButton
import com.example.v.ui.components.NavigationTopAppBar

@Composable
fun MainScreen(
    onClick: () -> Unit
) {
    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            var appName = LocalSharedStateLanguage.current
            NavigationTopAppBar(appName.value, NavigationItems.Menu,mutableListOf(NavigationItems.Search,
                NavigationItems.More),null,onClick)
        },
        floatingActionButton = {
            CastFloatingActionButton(Modifier.size(60.dp))
        }
    ) { gg ->
    }
}