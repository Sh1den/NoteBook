package com.example.v.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.v.data.model.Category

fun NavDestination.isTopLevelRoute(): Boolean{
    return this.hasRoute<Route.HomeScreen>() || this.hasRoute<Route.FolderScreen>()
}