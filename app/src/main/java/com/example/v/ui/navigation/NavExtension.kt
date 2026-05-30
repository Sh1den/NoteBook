package com.example.v.ui.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute

inline fun NavDestination.isTopLevelRoute(): Boolean{
    return this.hasRoute<Route.HomeScreen>() ?: false || this.hasRoute<Route.FolderScreen>() ?: false
}
