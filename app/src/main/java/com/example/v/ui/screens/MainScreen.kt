package com.example.v.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.v.ui.components.CastFloatingActionButton
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.DrawItem
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onClick: () -> Unit
) {
        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            topBar = {
                NavigationTopAppBar("NoteBook", NavigationItems.Menu,mutableListOf(NavigationItems.Search,
                    NavigationItems.More),null,onClick)
            },
            floatingActionButton = {
                CastFloatingActionButton(Modifier.size(60.dp))
            }
        ) {
        }
}