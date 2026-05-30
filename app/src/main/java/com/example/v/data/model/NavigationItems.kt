package com.example.v.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.v.R

sealed class NavigationItems(
    val title: Int? = null,
    val imageVector: ImageVector? = null,
    val painter: Int? = null
){
    object Search: NavigationItems(
        imageVector = Icons.Default.Search
    )
    object More: NavigationItems(
        imageVector = Icons.Default.MoreVert
    )
    object Back: NavigationItems(
        imageVector = Icons.Filled.ArrowBack
    )
    object Menu: NavigationItems(
        imageVector = Icons.Default.Menu
    )
    object Basket: NavigationItems(
        title = R.string.basket_app,
        painter = R.drawable.outline_delete_24
    )
    object Home: NavigationItems(
        title = R.string.Home,
        painter = R.drawable.outline_home_24
    )
    object Setting: NavigationItems(
        title = R.string.setting,
        painter = R.drawable.outline_settings_24
    )
    object Folder: NavigationItems(
        title = R.string.packege,
        painter = R.drawable.outline_folder_24
    )
    object NewFolder: NavigationItems(
        title = R.string.new_packege,
        painter = R.drawable.outline_create_new_folder_24
    )
}