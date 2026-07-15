package com.example.v.ui.navigation
import com.example.v.data.model.Table
import com.example.v.data.model.TypeCategory
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SettingsScreen
    @Serializable
    data class HomeScreen(
        val stringCategory: String = "Main",
        val typeCategory: TypeCategory = TypeCategory.MAIN
    )
    @Serializable
    object FolderScreen
    @Serializable
    data class NoteScreen(
        var id: Int? = null,
        val stringCategory: String = "Main",
        val typeCategory: TypeCategory = TypeCategory.MAIN
    )
}

