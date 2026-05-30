package com.example.v.ui.navigation

import android.util.Log
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.v.ui.components.AppDrawerContent
import com.example.v.ui.screens.FolderScreen
import com.example.v.ui.screens.MainScreen
import com.example.v.ui.screens.SettingsScreen
import kotlinx.coroutines.launch

@Composable
fun NavAppGraph(navController: NavHostController) {
    var scope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val thScreen = backStackEntry?.destination
    val isTopLevel = thScreen?.isTopLevelRoute() ?: false
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    Log.d("Nav",isTopLevel.toString())
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = isTopLevel,
        drawerContent = {
            AppDrawerContent(navController,thScreen){
                scope.launch { drawerState.close() }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen
        ) {

            composable<Route.SettingsScreen> {
                SettingsScreen(navController = navController){
                }
            }
            composable<Route.HomeScreen> {
                MainScreen(){
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
            composable<Route.FolderScreen>{
                FolderScreen {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
        }
    }
}