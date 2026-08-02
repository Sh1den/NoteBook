package com.example.v.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.v.data.model.Category
import com.example.v.ui.components.AppDrawerContent
import com.example.v.ui.screens.AddNoteScreen
import com.example.v.ui.screens.BasketScreen
import com.example.v.ui.screens.FolderCategoryScreen
import com.example.v.ui.screens.FolderScreen
import com.example.v.ui.screens.MainScreen
import com.example.v.ui.screens.SettingsScreen
import com.example.v.ui.viewmodels.EditNoteViewModel
import com.example.v.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun NavAppGraph(
    navController: NavHostController
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val thScreen = backStackEntry?.destination
    val isTopLevel = thScreen?.isTopLevelRoute() ?: false
    val drawerState = rememberDrawerState(DrawerValue.Closed)
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
                        ) { -it}
                    },
                    popEnterTransition =  {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { -it }
                    },
                    popExitTransition =  {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { it}
                    }
                ) {
                    SettingsScreen(navController = navController)
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
                    MainScreen(navController, mainViewModel) {
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
                    FolderScreen(navController) {
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
                        ) { -it}
                    },
                    popEnterTransition =  {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { -it }
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { it }
                    }
                ) {
                    val editNoteViewModel: EditNoteViewModel = hiltViewModel()
                    AddNoteScreen(navController,editNoteViewModel)
                }
                composable<Route.FolderNotes>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { -it }
                    },
                    popEnterTransition =  {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { -it }
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { it }
                    }
                ) {
                    val route = it.toRoute<Route.FolderNotes>()
                    val category = Category.getCategory(route.stringCategory,route.categoryId)
                    FolderCategoryScreen(navController,category,mainViewModel) {
                        navController.popBackStack()
                    }
                }
                composable<Route.BasketNotes>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { it }
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400,easing = LinearOutSlowInEasing)
                        ) { -it }
                    },
                    popEnterTransition =  {
                        slideInHorizontally(
                            animationSpec = tween(400, easing = LinearOutSlowInEasing)
                        ) { -it }
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(400, easing = FastOutLinearInEasing)
                        ) { it }
                    }
                ) {
                    BasketScreen(navController,mainViewModel) {
                        navController.navigate(Route.HomeScreen){
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                }
            }
            BackHandler(drawerState.isOpen) {
                scope.launch { drawerState.close() }
            }
        }
    }
}