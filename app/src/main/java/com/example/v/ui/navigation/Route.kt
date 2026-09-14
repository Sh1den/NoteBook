package com.example.v.ui.navigation
import androidx.annotation.Keep
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SettingsScreen
    @Serializable
    object HomeScreen
    @Serializable
    object FolderScreen
    @Serializable
    data class NoteScreen(
        var id: Int? = null,
        val foreignKey: Int? = null
    )
    @Serializable
    data class FolderNotes(
        val stringCategory: String = "",
        val id: Int = 0
    )
    @Serializable
    object BasketNotes
}

