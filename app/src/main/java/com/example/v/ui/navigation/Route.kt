package com.example.v.ui.navigation
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SettingsScreen
    @Serializable
    object HomeScreen{
    }
    @Serializable
    object FolderScreen
}