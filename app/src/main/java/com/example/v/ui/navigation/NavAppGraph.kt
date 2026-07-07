package com.example.v.ui.navigation

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.v.ui.components.AppDrawerContent
import com.example.v.ui.screens.AddNoteScreen
import com.example.v.ui.screens.FolderScreen
import com.example.v.ui.screens.MainScreen
import com.example.v.ui.screens.SettingsScreen
import kotlinx.coroutines.launch

@Composable
fun NavAppGraph(
    navController: NavHostController
) {
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
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            NavHost(
                navController = navController,
                startDestination = Route.HomeScreen
            ) {

                composable<Route.SettingsScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { -it / 4 }
                    },
                    popEnterTransition =  {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    popExitTransition =  {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { -it}
                    }
                ) {
                    SettingsScreen(navController = navController) {
                    }
                }
                composable<Route.HomeScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400,easing = LinearOutSlowInEasing)
                        ) { -it }
                    }
                ) {
                    MainScreen(navController) {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                }
                composable<Route.FolderScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400,easing = LinearOutSlowInEasing)
                        ) { -it }
                    }
                ) {
                    FolderScreen {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                }
                composable<Route.NoteScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { -it / 4 }
                    },
                    popEnterTransition =  {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { -it }
                    }
                ) {
                    AddNoteScreen(navController)
                }
            }
        }
    }
}