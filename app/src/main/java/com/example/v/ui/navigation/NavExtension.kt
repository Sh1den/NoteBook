package com.example.v.ui.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute

fun NavDestination.isTopLevelRoute(): Boolean{
    return this.hasRoute<Route.HomeScreen>() || this.hasRoute<Route.FolderScreen>()
}
