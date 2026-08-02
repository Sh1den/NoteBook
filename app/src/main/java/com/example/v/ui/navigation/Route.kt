package com.example.v.ui.navigation
import androidx.annotation.Keep
import com.example.v.data.model.TypeCategory
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SettingsScreen
    @Serializable
    object HomeScreen
    @Serializable
    object FolderScreen
    @Serializable
    @Keep
    data class NoteScreen(
        var id: Int? = null,
        val foreignKey: Int = 1,
        val stringCategory: String = "Main",
        val typeCategory: TypeCategory = TypeCategory.MAIN
    )
    @Serializable
    @Keep
    data class FolderNotes(
        val stringCategory: String = "",
        val categoryId: Int = 0
    )
    @Serializable
    object BasketNotes
}

